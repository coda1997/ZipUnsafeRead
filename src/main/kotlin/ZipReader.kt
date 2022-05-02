import java.io.File
import java.util.Arrays
import java.util.zip.InflaterOutputStream

fun extract(): ByteArray {
    val file = File("test.zip")
    val reader = file.bufferedReader()
    val header = CharArray(30)
    reader.read(header)
    val zipHeader = parseHeader(header)
    val nameBuffer = CharArray(zipHeader.fileNameSize)
    reader.read(nameBuffer)
    val data = CharArray(zipHeader.compressedSize)
    reader.read(data)
    val imgData = unzip(data, zipHeader.uncompressedSize)
    println(nameBuffer.joinToString(""))
    return byteArrayOf()
}

fun parseHeader(header: CharArray): ZipFileHeader {
    return ZipFileHeader(
        fileNameSize = header.toSmallEndianInt(26, 28),
        compressedSize = header.toSmallEndianInt(18, 22),
        uncompressedSize = header.toSmallEndianInt(22, 26),
        compressionMethod = header.toSmallEndianInt(8, 10)
    )
}

fun unzip(data:CharArray, uncomSize:Int):ByteArray{
    val inflater = java.util.zip.Inflater()
    inflater.setInput(data.map { it.code.toByte() }.toByteArray())
    val res = ByteArray(uncomSize)
    inflater.inflate(res)
    return res
}

fun CharArray.toSmallEndianInt(from: Int, to: Int): Int {
    if (to < from) {
        return -1
    }
    var res = 0
    for (i in (to - 1) downTo from) {
        res = res * 16 + this[i].code
    }
    return res
}


fun main() {
    extract()
}