package com.ismailamassi.data.db

object DatabaseErrorName {
    const val INSERT_ERROR_CODE = -1L
    const val UPDATE_ERROR_CODE = 0
    const val DELETE_ERROR_CODE = 0
    val GET_ERROR = null

    const val ERROR_INSERT = "ERROR_INSERT"
    const val MULTIPLE_ERROR_INSERT = "MULTIPLE_ERROR_INSERT"
    const val ERROR_UPDATE = "ERROR_UPDATE"
    const val MULTIPLE_ERROR_UPDATE = "MULTIPLE_ERROR_UPDATE"
    const val ERROR_DELETE = "ERROR_DELETE"
    const val MULTIPLE_ERROR_DELETE = "MULTIPLE_ERROR_DELETE"
    const val ERROR_GET = "ERROR_GET"
}