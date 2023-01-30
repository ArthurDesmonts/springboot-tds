package models

class UIMessage {
    class Message constructor( var title : String, var message : String,var icon : String, var type : String) {

    }

    companion object {
        fun message(title: String, message: String) : Message = Message( title, message,"info circle", "success")


    }


}