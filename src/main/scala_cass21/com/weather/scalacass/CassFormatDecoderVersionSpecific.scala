package com.weather.scalacass

object CassFormatDecoderVersionSpecific extends CassFormatDecoderVersionSpecific

trait CassFormatDecoderVersionSpecific extends LowPriorityCassFormatDecoder {
  import CassFormatDecoder.sameTypeCassFormatDecoder

  implicit val dateFormat: CassFormatDecoder[java.util.Date] =
    sameTypeCassFormatDecoder[java.util.Date](classOf[java.util.Date], _ getDate _, _ getDate _)
}