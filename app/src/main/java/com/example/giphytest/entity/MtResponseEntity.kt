package com.example.giphytest.entity

data class MtResponseEntity(
    val message: String,
    val status: String,
    val value: Value
)

data class Value(
    val count: Int,
    val `data`: List<Data>
)

data class Data(
    val assign_date: String,
    val assigned_by: String,
    val brand: String,
    val company_name: String,
    val current_assign_userid: Int,
    val department: String,
    val id: Int,
    val inv: List<Inv>,
    val inv_type: String,
    val inv_type_id: Int,
    val item_code: String,
    val purchase_date: String,
    val remark: String,
    val return_date: String,
    val status: String,
    val user_name: String,
    val warranty: Int,
    val warranty_status: String
)

data class Inv(
    val code: String,
    val `field`: List<Field>,
    val id: Int,
    val inventory: String
)

data class Field(
    val field_lable: String,
    val field_value: Any,
    val id: Int
)