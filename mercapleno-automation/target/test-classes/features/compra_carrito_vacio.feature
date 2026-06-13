# language: es
Característica: Intentar comprar con carrito vacío en Mercapleno

  Escenario: Intentar realizar el pago sin productos agregados
    Dado que el usuario inicia sesion con correo "pablo@gmail.com" y contrasena "123456"
    Cuando el usuario ingresa al carrito de compras
    Y decide pagar sin agregar ningun producto
    Entonces deberia ver un mensaje de error indicando "Tu carrito esta vacio."
