import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import * as express from 'express';
import * as path from 'path';
import * as fs from 'fs';
import { AppModule } from './app.module';
import { envs } from './config';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // Asegurar que exista la carpeta de uploads para las imágenes de productos
  const uploadsDir = path.join(process.cwd(), 'uploads');
  if (!fs.existsSync(uploadsDir)) {
    fs.mkdirSync(uploadsDir, { recursive: true });
  }

  // Servir estáticos de la carpeta de uploads
  app.use('/uploads', express.static(uploadsDir));

  app.setGlobalPrefix('api');
  app.enableCors();
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true,
      forbidNonWhitelisted: true,
      transform: true,
      transformOptions: {
        enableImplicitConversion: true,
      },
    }),
  );

  const swaggerConfig = new DocumentBuilder()
    .setTitle('Mercapleno API')
    .setDescription('Backend migrado a NestJS para Mercapleno')
    .setVersion('2.0.0')
    .addBearerAuth()
    .build();

  const document = SwaggerModule.createDocument(app, swaggerConfig);
  SwaggerModule.setup('api/docs', app, document);

  await app.listen(envs.port);
  // eslint-disable-next-line no-console
  console.log(`Mercapleno backend corriendo en http://localhost:${envs.port}`);
  // eslint-disable-next-line no-console
  console.log(`Swagger: http://localhost:${envs.port}/api/docs`);
}

bootstrap();
