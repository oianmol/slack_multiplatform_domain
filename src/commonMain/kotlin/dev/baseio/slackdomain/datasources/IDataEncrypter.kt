package dev.baseio.slackdomain.datasources

interface IDataEncrypter {
    fun encrypt(byteArray: ByteArray):ByteArray
}

interface IDataDecryptor{
    fun decrypt(byteArray: ByteArray):ByteArray
}