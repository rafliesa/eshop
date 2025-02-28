<details>
<summary>Module 1</summary>

## Reflection 1

**Question:** You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code. If you find any mistake in your source code, please explain how to improve your code.

Setelah mengerjakan modul ini, saya menjadi lebih suka menuliskan kode yang jelas maksudnya atau straightforward dibandingkan dengan kode yang memerlukan komentar. Selain itu, saya juga mulai menerapkan beberapa coding standard, seperti penamaan variabel yang jelas dan sesuai dengan naming convention. Tidak hanya itu, saya telah mengadopsi beberapa prinsip secure coding, seperti menggunakan UUID untuk ID daripada menggunakan integer yang diincrement.

## Reflection 2

**Question:** After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

Setelah menulis unit test, saya jadi merasa bahwa unit test sangat diperlukan terutama jika kita sedang mengerjakan suatu proyek yang scalable.

Tidak ada aturan baku soal jumlah unit test dalam satu kelas, tapi idealnya setiap metode penting dalam kelas tersebut memiliki minimal satu unit test. Jika terdapat banyak skenario, maka sebaiknya dibuat test untuk setiap kemungkinan, seperti input valid, input tidak valid, nilai null, dan sebagainya. Selain itu, penting untuk memastikan bahwa semua fitur diuji secara menyeluruh, termasuk edge case yang jarang terjadi, agar program dapat bekerja dengan baik dalam berbagai kondisi.

100% code coverage tidak berarti aman dari bug. Meskipun semua baris kode telah dieksekusi dalam pengujian, hal ini tidak menjamin bahwa program bebas dari kesalahan. Salah satu alasannya adalah kemungkinan hanya menguji skenario yang "aman", sementara edge case yang jarang terjadi belum diuji.

## Reflection 3

**Question:** Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.

Menambahkan fungsional test suite baru dengan setup yang sama dapat menyebabkan duplikasi kode, mengurangi keterbacaan, dan menyulitkan pemeliharaan. Hal ini melanggar prinsip DRY (Don't Repeat Yourself), meningkatkan kompleksitas, dan berisiko menciptakan inkonsistensi. Untuk menjaga kebersihan kode, setup umum sebaiknya diekstrak ke superclass atau metode utilitas yang dapat digunakan ulang. Selain itu, jika dua test suite memiliki struktur serupa, lebih baik menggabungkannya dalam satu kelas dengan beberapa metode pengujian untuk menghindari redundansi.

</details>

<details>
<summary>Module 2</summary>

## Reflection

**Question:** List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

1. Pinned dependencies -> pastikan setiap versi actions terikat oleh hashing commit.
2. SAST -> Menggunakan codeql untuk mengotomatiskan pemeriksaan keamanan.
3. License -> Menambahkan license untuk menjelaskan legalitas repository.

**Question:** Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Menurut saya, proyek ini sudah mengimplementasikan CI/CD sesuai definisinya. Saya telah menerapkan Continuous Integration (CI) dengan otomatisasi proses build dan testing. Karena saya menggunakan Koyeb untuk deployment, setiap ada perubahan atau commit baru di GitHub akan terdeteksi secara otomatis dan langsung memicu proses deployment.

</details>

<details>
<summary>Module 3</summary>
## Reflection 

### 1) Explain what principles you apply to your project!
Untuk membuat kode menjadi lebih terstruktur, saya memastikan project ini sudah memenuhi prinsip SOLID. Yakni:
1. Single Responsibility Principle (SRP) <br>
   Saya memastikan bahwa setiap class menjalankan tanggung jawabnya sendiri. Terlihat bahwa saya memecahkan controller, repository, dan service khusus untuk Product dan Car. <br>
2. Open/Closed Principle (OCP) <br>
    Saya membuat model abstract BaseModel untuk memungkinkan model lain melakukan ekstensi tanpa memodifikasi kode yang sudah ada, sehingga kode menjadi lebih modular.
3. Liskov Substitution Principle (LSP) <br>
   Untuk setiap subclass, saya tidak mengubah perilaku methods yang sudah didefinisikan pada superclass. Jadi setiap subclas melakukan hal yang percis sama pada method yang terdefinisi di superclassnya.
4. Interface Segregation Principle (ISP) <br>
    Untuk project ini, karena repository dan service untuk kedua model memiliki implementasi yang sama percis, saya menggabungkan keduanya. Namun jika terdapat fungsi yang beda dan hanya spesiifik untuk satu kelas, interface harus dipecah, jangan memaksakan fungsi yang tidak masuk akal pada suatu kelas.
5. Dependency Inversion Principle (DIP) <br>
    Setiap kali mendeklarasikan suatu kelas yang memiliki interface atau kelas abstrak, saya selalu menggunakann interface atau kelas abstractnya sebagai tipe data dari object tersebut. <br><br>

### 2) Explain the advantages of applying SOLID principles to your project with examples.
Dengan menggunakan prinsip SOLID, project saya menjadi: <br>
1. Lebih mudah di-mantain <br>
    Dengan prinsip SRP, jika saya ingin mengubah fungsi edit di repository Car, saya tidak perlu takut akan munculnya bug pada repostiory Product karena keduanya terdapat pada class yang berbeda.
2. Lebih mudah diperluas <br>
   Jika saya ingin menambahkan metode yang berkaitan dengan class produk, saya bisa menggunakan polymorphism tanpa mengubah kelas utama.
3. Bugfree / bug less pada inheritance <br>
   Dengan LSP, dapat dipastikan bahwa subclass dapat menggantikan superclass tanpa masalah karena perilaku-perilaku superclass tidak diubah pada subclass.

### 3) Explain the disadvantages of not applying SOLID principles to your project with examples.
1. Lebih sulit di-maintain <br>
   Dengan prinsip SRP, jumlah class dan file dalam proyek bisa bertambah secara signifikan. Jika tidak dikelola dengan baik, ini bisa membuat navigasi dan pemahaman kode lebih sulit.
2. Lebih sulit diperluas dalam beberapa situasi <br>
   Jika ingin menambahkan metode yang berkaitan dengan suatu class, terkadang harus membuat banyak interface atau abstraksi tambahan agar tetap mengikuti OCP. Hal ini bisa memperumit kode daripada sekedar menambahkan metode langsung ke kelas yang ada.
3. Potensi bug lebih tinggi akibat fragmentasi kode <br>
   Dengan LSP, memastikan setiap subclass benar-benar dapat menggantikan superclass tanpa mengubah perilaku bisa menjadi tantangan. Jika aturan ini dilanggar tanpa disadari, bug bisa muncul di tempat yang sulit dilacak, terutama pada sistem yang kompleks.

</details>
