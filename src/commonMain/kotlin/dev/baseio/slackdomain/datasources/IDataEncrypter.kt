package dev.baseio.slackdomain.datasources

interface IDataEncrypter {
    fun encrypt(byteArray: ByteArray, publicKeyBytes: ByteArray,chainId: String): ByteArray
}

interface IDataDecryptor {
    fun decrypt(byteArray: ByteArray,chainId:String): ByteArray
}