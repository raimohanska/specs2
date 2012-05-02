package org.specs2
package io

/**
 * This trait provides implicit conversions from strings to a Path object providing path-related functions like
 * setting all the separators as UNIX separators.
 */
private[specs2]
trait Paths { outer =>
  implicit def toPath(s: String) = Path(s)
  def dirPath(s: String) = {
    val normalized = s.normalize
    if (normalized.endsWith("/")) normalized
    else normalized + "/"

  }
  def normalize(s: String) = s.replace("\\", "/")
  def baseDir(s: String) = "./"+("../" * (s.normalize.split("/").filterNot(_.isEmpty).size - 1))
}

case class Path(s: String) {
  def dirPath = Paths.dirPath(s)
  def normalize = Paths.normalize(s)
  def baseDir = Paths.baseDir(s)
}

private[specs2]
object Paths extends Paths