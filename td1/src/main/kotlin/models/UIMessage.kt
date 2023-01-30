package models

class UIMessage {
    class Message constructor(var icon : String, var type : String, var title : String, var message : String) {

    }

    companion object {
        fun message(title: String, message: String) : Message = Message( title, message,"info circle", "success")


    }


}