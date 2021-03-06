package com.weather.scalacass

import com.datastax.driver.core.{Row, TupleValue}

object CassFormatDecoderVersionSpecific extends CassFormatDecoderVersionSpecific {
  def codecCassFormatDecoder[T <: AnyRef](_clazz: Class[T]) = new CassFormatDecoder[T] {
    type From = T
    val clazz = _clazz
    def f2t(f: From) = Right(f)
    def extract(r: Row, name: String) = r get (name, clazz)
    def tupleExtract(tup: TupleValue, pos: Int) = tup get (pos, clazz)
  }
}
trait CassFormatDecoderVersionSpecific extends LowPriorityCassFormatDecoder {
  import CassFormatDecoder.{sameTypeCassFormatDecoder, safeConvertCassFormatDecoder}
  implicit val dateFormat: CassFormatDecoder[java.util.Date] =
    sameTypeCassFormatDecoder[java.util.Date](classOf[java.util.Date], _ getTimestamp _, _ getTimestamp _)
  implicit val datastaxLocalDateFormat: CassFormatDecoder[com.datastax.driver.core.LocalDate] =
    sameTypeCassFormatDecoder[com.datastax.driver.core.LocalDate](classOf[com.datastax.driver.core.LocalDate], _ getDate _, _ getDate _)
  implicit val timeFormat: CassFormatDecoder[Time] = safeConvertCassFormatDecoder[Time, java.lang.Long](classOf[java.lang.Long], Time.apply(_), _ getTime _, _ getTime _)
}