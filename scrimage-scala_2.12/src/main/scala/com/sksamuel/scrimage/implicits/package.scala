package com.sksamuel.scrimage

import java.awt.{Color, Graphics2D}
import java.io.File
import java.nio.file.Path

import com.sksamuel.scrimage.canvas.{Canvas, GraphicsContext}
import com.sksamuel.scrimage.graphics.RichGraphics2D
import com.sksamuel.scrimage.nio.{ImageWriter, PngWriter}
import com.sksamuel.scrimage.pixels.Pixel

import scala.language.implicitConversions

package object implicits {

   implicit val writer: ImageWriter = PngWriter.MaxCompression
   implicit def color2awt(color: com.sksamuel.scrimage.color.Color): java.awt.Color = color.awt()

   implicit def toCanvas(image: ImmutableImage): Canvas = new Canvas(image)
   implicit def toImage(canvas: Canvas): ImmutableImage = canvas.getImage

   implicit def toGraphicsContext(fn: Graphics2D => Unit): GraphicsContext = new GraphicsContext {
      override def configure(g2: RichGraphics2D): Unit = fn(g2)
   }

   implicit class RichImmutableImage(image: ImmutableImage) {

      def map(f: Pixel => Color): ImmutableImage = {
         val fn: java.util.function.Function[Pixel, Color] = (t: Pixel) => f(t)
         image.map(fn)
      }

      def forall(f: Pixel => Boolean): Boolean = {
         val fn: java.util.function.Predicate[Pixel] = (t: Pixel) => f(t)
         image.forAll(fn)
      }

      def foreach(f: Pixel => Unit): Unit = {
         val fn: java.util.function.Consumer[Pixel] = (t: Pixel) => f(t)
         image.forEach(fn)
      }

      def output(file: File)(implicit writer: ImageWriter): Unit = {
         image.output(writer, file)
      }

      def output(path: Path)(implicit writer: ImageWriter): Unit = {
         image.output(writer, path)
      }

      def output(path: String)(implicit writer: ImageWriter): Unit = {
         image.output(writer, path)
      }

      def bytes(implicit writer: ImageWriter): Unit = {
         image.bytes(writer)
      }
   }
}