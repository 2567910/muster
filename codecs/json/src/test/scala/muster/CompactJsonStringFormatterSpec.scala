package muster

//import org.scalacheck._
import java.util.TimeZone
import org.specs2.{ScalaCheck, Specification}
import org.scalacheck.{Arbitrary, Gen}
//import org.joda.time.DateTime
//import org.joda.time.DateTimeZone


class CompactJsonStringFormatterSpec extends {val format = JsonTestFormat} with StringOutputFormatterSpec {

  val listProp = prop { (lst: List[Int]) =>
    withFormatter { fmt =>
      fmt.startArray("List")
      lst foreach fmt.int
      fmt.endArray()
      fmt.result must_== lst.mkString("[", ",", "]")
    }
  }

  val objectProp = prop { (obj: Category) =>
    withFormatter { fmt =>
      fmt.startObject("Category")
      fmt.startField("id")
      fmt.int(obj.id)
      fmt.startField("name")
      fmt.string(obj.name)
      fmt.endObject()
      fmt.result must_== s"""{"id":${obj.id},"name":"${obj.name}"}"""
    }
  }

  val is = br ^ "The compact json string formatter should" ^ br ^ sharedFragments ^ end
}