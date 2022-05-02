import java.io.BufferedOutputStream

class ZipFile: CompressionFile {
    override fun extract(): ByteArray {
        return byteArrayOf()
    }
    fun parseHeader(header:ByteArray) {

    }


}

data class ZipFileHeader(val fileNameSize: Int, val compressedSize:Int, val uncompressedSize:Int, val compressionMethod:Int)

interface CompressionFile {
    fun extract() : ByteArray
}