package br.com.app_my_manage_mobile

data class Response(val status: String, val msg: String) {
    fun isOK() = "OK".equals(status, ignoreCase = true)
}