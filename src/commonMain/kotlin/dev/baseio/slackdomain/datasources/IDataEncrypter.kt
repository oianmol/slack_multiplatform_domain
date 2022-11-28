package dev.baseio.slackdomain.datasources

interface IDataEncrypter {
    fun encrypt(byteArray: ByteArray, publicKeyBytes: ByteArray): ByteArray
}

interface IDataDecryptor {
    fun decrypt(byteArray: Pair<ByteArray,ByteArray>, privateKeyBytes: ByteArray): ByteArray
}