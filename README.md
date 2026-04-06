# 📁 Spring Boot File Upload & Download Project

This project demonstrates how to **upload and download files** in a Spring Boot application using REST APIs.

---

## 🚀 Features

* Upload files using `multipart/form-data`
* Download files from server
* Store files locally
* Configurable file size limits
* Clean layered architecture (Controller → Service)

---

## 🛠️ Tech Stack

* Java 8+
* Spring Boot
* Spring Web
* Lombok

---

## 📂 Project Structure

```
spring-file-demo
 └── controller
 └── service
 └──  service   service  
 └── resources
```

---

## ⚙️ Configuration

Update `application.properties`:

```properties
server.port=8080

spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB

file.upload-dir=uploads
```

---

## 📤 File Upload API

**Endpoint:**

```
POST /file/upload
```

**Consumes:**

```
multipart/form-data
```

### ▶️ cURL Request (Upload File)

```bash
curl -X POST "http://localhost:8080/file/upload" \
  -H "Content-Type: multipart/form-data" \
  -F "file=@robots.csv"
```

👉 Replace `robots.csv` with your file path if needed:

```
-F "file=@/full/path/to/robots.csv"
```

**Response:**

```
File uploaded successfully: robots.csv
```

---

## 📥 File Download API

**Endpoint:**

```
GET /file/download/{fileName}
```

### ▶️ cURL Request (Download File)

```bash
curl -X GET "http://localhost:8080/file/download/robots.csv" \
  -O
```

👉 `-O` will save the file with the same name in your current directory.

---

## 🧪 Testing

### Upload File

1. Open Postman
2. Select POST
3. URL: `http://localhost:8080/file/upload`
4. Body → form-data → choose file

---

### Download File

1. Open browser/Postman
2. Hit:

```
http://localhost:8080/file/download/robots.csv
```

---

## 📌 Important Concepts

* `MultipartFile` → used for file upload
* `Resource` → used for file download
* `Content-Disposition` → forces file download
* `Files.copy()` → saves file efficiently

---

## 🔥 Future Enhancements

* Store file metadata in DB
* Upload to AWS S3
* File validation (type/size)
* Async file processing
* Large file streaming

---

## 👩‍💻 Author

Your Name

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
