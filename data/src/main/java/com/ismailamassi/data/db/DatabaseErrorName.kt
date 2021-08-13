package com.ismailamassi.data.db

object DatabaseErrorName {
    const val INSERT_ERROR_CODE = -1L
    const val UPDATE_ERROR_CODE = 0
    const val DELETE_ERROR_CODE = 0
    val GET_ERROR = null

    const val INSERT_ERROR_MESSAGE = "ERROR_INSERT"
    const val MULTIPLE_INSERT_ERROR_MESSAGE = "MULTIPLE_ERROR_INSERT"
    const val UPDATE_ERROR_MESSAGE = "ERROR_UPDATE"
    const val MULTIPLE_UPDATE_ERROR_MESSAGE = "MULTIPLE_ERROR_UPDATE"
    const val DELETE_ERROR_MESSAGE = "ERROR_DELETE"
    const val MULTIPLE_DELETE_ERROR_MESSAGE = "MULTIPLE_ERROR_DELETE"
    const val ERROR_GET_MESSAGE = "ERROR_GET"
}