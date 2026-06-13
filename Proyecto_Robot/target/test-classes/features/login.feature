# language: es
Característica: Flujo de compra SauceDemo

  Escenario: Compra exitosa y reporte de errores
    Dado el usuario abre la pagina
    Cuando ingresa usuario "standard_user" y password "secret_sauce"
    Y el usuario agrega un producto al carrito
    Y el usuario intenta realizar el pago
    Entonces valida que la compra fue exitosa con el mensaje "Thank you for your order!"