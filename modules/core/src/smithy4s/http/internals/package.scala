/*
 *  Copyright 2021 Disney Streaming
 *
 *  Licensed under the Tomorrow Open Source Technology License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     https://disneystreaming.github.io/TOST-1.0.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package smithy4s
package http

package object internals {

  private[http] type HttpCode[A] = (A, Hints) => Option[Int]

  private[internals] implicit class vectorOps[A](val vector: Vector[A])
      extends AnyVal {
    def traverse[B](f: A => Option[B]): Option[Vector[B]] =
      vector.foldLeft[Option[Vector[B]]](Some(Vector.empty)) { (result, a) =>
        for {
          acc <- result
          b <- f(a)
        } yield (acc.:+(b))
      }

    def traverse[E, B](f: A => Either[E, B]): Either[E, Vector[B]] =
      vector.foldLeft[Either[E, Vector[B]]](Right(Vector.empty)) {
        (result, a) =>
          for {
            acc <- result
            b <- f(a)
          } yield (acc.:+(b))
      }
  }

  private[internals] implicit class listOps[A](val list: List[A])
      extends AnyVal {
    def traverse[B](f: A => Option[B]): Option[List[B]] =
      list.foldLeft[Option[List[B]]](Some(List.empty)) { (result, a) =>
        for {
          acc <- result
          b <- f(a)
        } yield (acc.:+(b))
      }
  }

}
