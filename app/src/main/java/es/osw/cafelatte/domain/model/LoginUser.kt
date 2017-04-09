package es.osw.cafelatte.domain.model

data class LoginUser(val token: String?, val secret: String?, val provider: String, var email:
String)
