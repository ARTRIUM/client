package com.example.everytranslation.data.service

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.everytranslation.db.AppDatabase
import com.example.everytranslation.db.dto.ChatRoom
import java.time.LocalDateTime
import java.util.logging.Logger

class StompEventListener(context : Context) {
    private val logger = Logger.getLogger(StompEventListener::class.java.name)

    private val messageApiService = MessageApiService.getNewInstance()
    private val eventApiService = EventApiService.getNewInstance()
    private val appDatabase = AppDatabase.getInstance(context)


    fun listenMessageAndInsertToDB(roomId : Int){
        // 원래는 시간을 기준으로 새로운 데이터를 요청해야함.
        MessageApiService.getNewInstance().getAllMessages(roomId){
            Thread() {
                appDatabase.messageDao().deleteAll()
                for (message in it) {
                    logger.info("database add message : $message")
                    appDatabase.messageDao().insert(message)
                }
            }.start()
        }

        messageApiService.subscribeRoom(roomId){
            appDatabase.messageDao().insert(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun listenInviteAndInsertToDB(myId : Int){
        // 이친구도 마찬가지
        RoomApiService.instance.getRooms(){
            Thread() {
                appDatabase.roomDao().deleteAll()
                for (room in it) {
                    appDatabase.roomDao().insert(room)
                }
            }.start()
        }

        eventApiService.subscribeToMyEvent(myId){
            logger.info("invite room : $it")
            val room = ChatRoom(it.roomId, it.roomName, LocalDateTime.now().toString(), "")
            appDatabase.roomDao().insert(room)
            listenMessageAndInsertToDB(room.roomId)
        }
    }

    fun doNotListenMessage(roomId: Int){
        messageApiService.deSubscribeRoom(roomId)
    }

    fun doNotListenAllMessage(){
        messageApiService.deSubscribeAll()
    }

    fun doNotListenInviteAll(myId: Int){
        eventApiService.unSubscribeToMyEvent(myId)
    }
}