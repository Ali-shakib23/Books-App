package com.example.book_read

class book {
    var name: String? = null
    var author: String?=null
    var type: String?=null
    var year: String?=null
    var image: String? = null
//    var id: String? =null
    constructor(){

    }
    constructor(name: String??, author:String??,type: String??, year: String , image: String??) {
        this.name = name
        this.author=author
        this.type=type
        this.year=year
        this.image = image
//        this.id=id
    }
}