# Cómo probar la autenticación con JWT
- Intentar listar los alumnos, debería fallar.
```
GET {{BASE_URL}}/api/alumnos HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc0FkbWluIjp0cnVlLCJzdWIiOiJudWV2b191c3VhcmlvIiwiaWF0IjoxNzU0NTk0MzA2LCJleHAiOjE3NTQ2ODA3MDZ9.3gICPw1-M4Ahz-ok6gQwSjzWxDs9WC4_4B3ChZfaH8A
```

- Crear un usuario
```
POST {{BASE_URL}}/api/users
Content-Type: application/json

{
  "username": "nuevo_usuario",
  "userPassword": "password123",
  "isAdmin": true
}
```
- Autenticarse con el nuevo usuario. La api retorna el token de acceso
```
POST {{BASE_URL}}/api/users/authenticate
Content-Type: application/json

{
  "username": "nuevo_usuario",
  "password": "password123"
}
```
- Intentar listar los alumnos nuevamente, usando el token obtenido, debería fallar.
```
GET {{BASE_URL}}/api/alumnos HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc0FkbWluIjp0cnVlLCJzdWIiOiJudWV2b191c3VhcmlvIiwiaWF0IjoxNzU0NTk2MDgzLCJleHAiOjE3NTQ2ODI0ODN9.RKjloiX0NwYVaUxwvz_tvsf5TJhXp3qWuEjqN6atwV8
```
- Probar con los demás endpoint, creando usuario admin y regulares, para verificar que se cumplen las restricciones definidas en la clase SecurityConfig.