package com.example.everytranslation.data.service

import com.example.everytranslation.data.model.EventInvite
import com.example.everytranslation.utils.MyStompClient
import com.google.gson.Gson
import io.reactivex.functions.Consumer


class EventApiService() {

    private val invitePrefix = "/pub/event/sub/"
    private val myEventPrefix = "/sub/"

    private var myStompService : MyStompClient = MyStompClient.createInstance()

    fun makeInviteEvent(myId: String, toId:String, eventInvite: EventInvite){
        myStompService.send(invitePrefix + toId, Gson().toJson(eventInvite)){
            if(!it) throw IllegalStateException("send chat message status is not 200")
        }
    }

    fun subscribeToMyEvent(myId: Int, callback: Consumer<EventInvite>){
        myStompService.subscribe(myEventPrefix + myId, EventInvite::class.java){
            callback.accept(it)
        }
    }

    fun unSubscribeToMyEvent(myId: Int){
        myStompService.disposeTopic(myEventPrefix + myId)
    }

    fun deSubscribeAll(){
        myStompService.disposeTopicAll()
    }

    companion object{
        fun getNewInstance() : EventApiService{
            return EventApiService()
        }
    }
}