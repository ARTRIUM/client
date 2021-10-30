package com.example.everytranslation.data.service



import android.util.Log
import com.example.everytranslation.data.ChatDTO
import com.example.everytranslation.data.service.util.rest.RestApiService
import com.example.everytranslation.data.service.util.rest.RestApiServiceCallback
import com.example.everytranslation.data.service.util.stomp.MyStompClient
import com.example.everytranslation.db.dto.Message
import java.util.function.Consumer
import java.util.logging.Logger

class MessageApiService() {
    private val logger = Logger.getLogger(MessageApiService.javaClass.name)

    private val sendEndpointPrefix = "/pub/chat/"
    private val receiveEndPointPrefix = "/sub/chat/room/"

    private var myStompService: MyStompClient = MyStompClient.createInstance()
    private var restApiService : RestApiService = RestApiService.instance

    fun sendMessage(userId: Int, roomId : Int, message: String){
        myStompService.send("$sendEndpointPrefix$userId/$roomId", message){
            if(!it) throw IllegalStateException("send chat message status is not 200")
        }
    }

    fun getAllMessages(roomId:Int,callback: Consumer<List<Message>>){
        restApiService.getMessages(roomId).enqueue(RestApiServiceCallback(callback))
    }

    // 현재방 이름에 대한 메세지를 들고오는 것 ( 아마 어플을 실행할때 쓰는 API )

//    fun getAllMessages(roomId: Int, callback: Consumer<List<ChatDTO>>){
//        restApiService.getMessages(roomId).enqueue(RestApiServiceCallback(callback))
//    }

    fun subscribeRoom(roomId: Int, callback: Consumer<Message>){
        myStompService.subscribe(receiveEndPointPrefix + roomId, Message::class.java){
            Log.d("전송한사람 {}",it.writtenAt!!)
            Log.d("전송한내용 {}",it.content)
            callback.accept(it)
        }
    }

    fun deSubscribeRoom(roomId: Int){
        myStompService.disposeTopic(receiveEndPointPrefix + roomId)
    }

    fun deSubscribeAll(){
        myStompService.disposeTopicAll()
    }

    companion object{
        fun getNewInstance() : MessageApiService{
            return MessageApiService()
        }
    }
}