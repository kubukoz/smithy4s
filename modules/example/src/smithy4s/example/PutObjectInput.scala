package smithy4s.example

import smithy4s.syntax._

case class PutObjectInput(key: ObjectKey, bucketName: BucketName, data: String, foo: Option[LowHigh] = None, someValue: Option[SomeValue] = None)
object PutObjectInput {
  def namespace: String = NAMESPACE
  val name: String = "PutObjectInput"

  val hints : smithy4s.Hints = smithy4s.Hints()

  val schema: smithy4s.Schema[PutObjectInput] = struct(
    ObjectKey.schema.required[PutObjectInput]("key", _.key).withHints(smithy.api.Required(), smithy.api.HttpLabel()),
    BucketName.schema.required[PutObjectInput]("bucketName", _.bucketName).withHints(smithy.api.Required(), smithy.api.HttpLabel()),
    string.required[PutObjectInput]("data", _.data).withHints(smithy.api.Required(), smithy.api.HttpPayload()),
    LowHigh.schema.optional[PutObjectInput]("foo", _.foo).withHints(smithy.api.HttpHeader("X-Foo")),
    SomeValue.schema.optional[PutObjectInput]("someValue", _.someValue).withHints(smithy.api.HttpQuery("paramName")),
  ){
    PutObjectInput.apply
  }
  implicit val staticSchema : schematic.Static[smithy4s.Schema[PutObjectInput]] = schematic.Static(schema)
}