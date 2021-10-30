package com.example.everytranslation.data.service



import com.example.everytranslation.data.ChatDTO
import com.example.everytranslation.data.service.util.rest.RestApiService
import com.example.everytranslation.data.service.util.stomp.MyStompClient
import io.reactivex.functions.Consumer
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

    // 현재방 이름에 대한 메세지를 들고오는 것 ( 아마 어플을 실행할때 쓰는 API )

//    fun getAllMessages(roomId: Int, callback: Consumer<List<ChatDTO>>){
//        restApiService.getMessages(roomId).enqueue(RestApiServiceCallback(callback))
//    }

    fun subscribeRoom(roomId: Int, callback: Consumer<ChatDTO>){
        myStompService.subscribe(receiveEndPointPrefix + roomId, ChatDTO::class.java){
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