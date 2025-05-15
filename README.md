## UTS_PBO2_223KA_23552011138

<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: Muhammad Rijal</li>
  <li>NIM: 23552011138</li>
  <li>Studi Kasus: Catetin Todo App (catatan)</li>
</ul>

## Judul Studi Kasus
<p>Aplikasi Pencatatan Kegiatan Berbasis Website</p>

## Penjelasan Studi Kasus
<p>
Aplikasi Pencatatan Kegiatan Berbasis Website merupakan sebuah sistem yang dirancang untuk membantu pengguna dalam mencatat berbagai kegiatan beserta tenggat waktunya, sehingga pengguna tidak melewatkan tugas atau aktivitas penting yang harus diselesaikan.</p>
<p>Aplikasi ini dilengkapi dengan fitur-fitur seperti menambah catatan baru, mengedit catatan, menghapus catatan, serta menandai kegiatan yang telah selesai. Dengan fitur-fitur tersebut, pengguna dapat lebih mudah mengelola dan memantau aktivitas sehari-hari secara tertata dan efisien.</p>

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Encapsulation adalah proses menyembunyikan detail implementasi dan hanya menampilkan antarmuka publik. Dalam studi kasus ini, setiap entitas seperti User dan ToDo menggunakan property private dan hanya dapat diakses melalui getter dan setter. Ini menjaga integritas data agar tidak diubah sembarangan dari luar class.</p>

### 2. Encapsulation
<p>Inheritance memungkinkan suatu class mewarisi method dan properti dari class induk. Misalnya, jika ada class BaseEntity yang menyimpan field umum seperti id dan createdAt, maka class User dan ToDo dapat mewarisinya agar tidak perlu menulis ulang kode yang sama. Ini membantu mengurangi duplikasi dan meningkatkan keteraturan struktur kode.</p>

### 3. Polymorphism
<p>Polymorphism memungkinkan objek untuk memiliki banyak bentuk. Dalam aplikasi ini, service seperti UserService dan ToDoService dapat mengimplementasikan interface yang sama dengan cara yang berbeda. Misalnya, method save() bisa diterapkan berbeda pada masing-masing entitas, sesuai kebutuhan bisnisnya.</p>

### 4. Abstraction
<p>Abstraction menyembunyikan kompleksitas dan hanya menampilkan bagian penting dari sebuah objek. Dalam studi kasus ini, penggunaan interface seperti UserRepository dan ToDoRepository menyembunyikan detail implementasi akses database, sehingga controller hanya perlu fokus pada logika bisnis tanpa memikirkan cara penyimpanan data.</p>

## Demo Proyek
<ul>
  <li>Github: <a href="https://github.com/mrijal/UTS_PBO1_223KA_23552011138" target="_blank">Github</a></li>
  <li>Youtube: <a href="https://youtu.be/hkDgD_QcavY" target="_blank">Youtube</a></li>
</ul>

