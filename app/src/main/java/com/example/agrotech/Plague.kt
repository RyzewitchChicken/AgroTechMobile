package com.example.agrotech

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Plague : AppCompatActivity() {
    private val STORAGE_CODE = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plague)


        val btnSave = findViewById<Button>(R.id.btSavePlague)

        btnSave.setOnClickListener{
            /*val intent = Intent(this, ViewPlot::class.java)
            startActivity(intent)*/
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission= arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission,STORAGE_CODE)
                } else {
                    savePDF()
                }
            } else {
                savePDF()
            }

        }
    }

    private fun savePDF() {

        val mFileName="Tratamiento" + SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
        val mFilePath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + mFileName + ".pdf"
        try {
            val writer=PdfWriter(FileOutputStream(mFilePath))
            val pdfDoc=PdfDocument(writer)
            val doc=Document(pdfDoc)
            doc.setMargins(0F,0F,0F,0F)


            val treat=findViewById<EditText>(R.id.comentary)
            val data=treat.text.toString().trim()
            val image=getDrawable(R.drawable.agrotech)
            val bitmap = (image as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
            val bitmapData = stream.toByteArray()
            val imageData = ImageDataFactory .create(bitmapData)
            val images = Image(imageData)
            images.scaleToFit(300f,300f)
            images.setHorizontalAlignment(HorizontalAlignment.CENTER)
            doc.add(images)
            val title=Paragraph("Tratamiento de Plaga").setBold().setFontSize(30f).setTextAlignment(TextAlignment.CENTER)
            doc.add(title)
            val width = floatArrayOf(250f, 250f)
            val table= Table(width)
            table.setHorizontalAlignment(HorizontalAlignment.CENTER)

            val dato=intent.getStringExtra("Plaga")
            table.addCell(Cell().add(Paragraph("Plaga")))
            table.addCell(Cell().add(Paragraph(dato)))


            table.addCell(Cell().add(Paragraph("Descripcion de Tratamiento")))
            table.addCell(Cell().add(Paragraph(data)))

            val dateF=DateTimeFormatter.ofPattern("dd/MM/yyyy")
            table.addCell(Cell().add(Paragraph("Fecha")))
            table.addCell(Cell().add(Paragraph(LocalDate.now().format(dateF).toString())))



            doc.add(table)

            doc.close()
            Toast.makeText(this@Plague,"$mFileName.pdf\n fue creado", Toast.LENGTH_LONG).show()

        } catch (e:Exception) {
            Toast.makeText(this@Plague,e.toString(), Toast.LENGTH_LONG).show()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            STORAGE_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    savePDF()
                } else {
                    Toast.makeText(this@Plague,"Permiso denegado", Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}