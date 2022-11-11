package dev.baseio.slackdomain.datasources

interface IDataEncrypter {
    fun encrypt(byteArray: ByteArray, publicKeyBytes: ByteArray): ByteArray
}

interface IDataDecryptor {
    fun decrypt(byteArray: ByteArray, privateKeyBytes: ByteArray): ByteArray
}