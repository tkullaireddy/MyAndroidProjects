package com.nareshittechnologies.frtdb



data class PersonData(val name:String, val age:Int){
    constructor() : this("data",3) {

    }
}


data class PersonDatawithID(val id:String, val name:String, val age:Int)