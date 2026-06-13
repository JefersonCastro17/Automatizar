import { Injectable } from '@nestjs/common';
import * as nodemailer from 'nodemailer';
import { envs } from '../config';

@Injectable()
export class EmailService {
  private transporter: nodemailer.Transporter | null = null;

  private getTransporter(): nodemailer.Transporter {
    if (this.transporter) {
      return this.transporter;
    }

    if (!envs.smtpUser || !envs.smtpPass) {
      throw new Error('SMTP no configurado: faltan SMTP_USER/SMTP_PASS');
    }

    if (!envs.smtpService && !envs.smtpHost) {
      throw new Error('SMTP no configurado: falta SMTP_HOST o SMTP_SERVICE');
    }

    const options = envs.smtpService
      ? {
          service: envs.smtpService,
          auth: {
            user: envs.smtpUser,
            pass: envs.smtpPass,
          },
        }
      : {
          host: envs.smtpHost,
          port: envs.smtpPort,
          secure: envs.smtpSecure,
          auth: {
            user: envs.smtpUser,
            pass: envs.smtpPass,
          },
        };

    this.transporter = nodemailer.createTransport(options);
    return this.transporter;
  }

  private fromAddress(): string {
    const from = envs.smtpFromEmail || envs.smtpUser;
    if (!from) {
      throw new Error('SMTP no configurado: falta SMTP_FROM_EMAIL');
    }

    return envs.appName ? `"${envs.appName}" <${from}>` : from;
  }

  async sendVerificationCode(email: string, code: string, ttlMin: number): Promise<void> {
    const transporter = this.getTransporter();
    await transporter.sendMail({
      from: this.fromAddress(),
      to: email,
      subject: `${envs.appName} - Codigo de verificacion`,
      text: `Tu codigo de verificacion es ${code}. Vence en ${ttlMin} minutos.`,
      html: `
        <div style="font-family: Arial, sans-serif; line-height: 1.5;">
          <p>Tu codigo de verificacion es:</p>
          <div style="font-size: 28px; font-weight: bold; letter-spacing: 2px;">${code}</div>
          <p>Este codigo vence en ${ttlMin} minutos.</p>
        </div>
      `,
    });
  }

  async sendPasswordResetCode(email: string, code: string, ttlMin: number): Promise<void> {
    const transporter = this.getTransporter();
    await transporter.sendMail({
      from: this.fromAddress(),
      to: email,
      subject: `${envs.appName} - Recuperar contrasena`,
      text: `Tu codigo para recuperar contrasena es ${code}. Vence en ${ttlMin} minutos.`,
      html: `
        <div style="font-family: Arial, sans-serif; line-height: 1.5;">
          <p>Tu codigo para recuperar contrasena es:</p>
          <div style="font-size: 28px; font-weight: bold; letter-spacing: 2px;">${code}</div>
          <p>Este codigo vence en ${ttlMin} minutos.</p>
        </div>
      `,
    });
  }

  async sendLowStockAlert(email: string, productName: string, stock: number, threshold: number): Promise<void> {
    const transporter = this.getTransporter();
    await transporter.sendMail({
      from: this.fromAddress(),
      to: email,
      subject: `⚠️ Alerta de Bajo Stock: ${productName}`,
      text: `El producto "${productName}" ha alcanzado un stock de ${stock} unidades, lo cual es inferior o igual al umbral establecido de ${threshold} unidades.`,
      html: `
        <div style="font-family: Arial, sans-serif; line-height: 1.5; border: 1px solid #ffccd5; padding: 20px; border-radius: 8px;">
          <h2 style="color: #d9383a; margin-top: 0;">⚠️ Alerta de Bajo Stock</h2>
          <p>El siguiente producto ha alcanzado un nivel crítico de existencias:</p>
          <table style="width: 100%; border-collapse: collapse; margin: 15px 0;">
            <tr style="background-color: #f8f9fa;">
              <td style="padding: 8px; font-weight: bold; border-bottom: 1px solid #dee2e6;">Producto:</td>
              <td style="padding: 8px; border-bottom: 1px solid #dee2e6;">${productName}</td>
            </tr>
            <tr>
              <td style="padding: 8px; font-weight: bold; border-bottom: 1px solid #dee2e6;">Stock Actual:</td>
              <td style="padding: 8px; border-bottom: 1px solid #dee2e6; color: #d9383a; font-weight: bold;">${stock} unidades</td>
            </tr>
            <tr style="background-color: #f8f9fa;">
              <td style="padding: 8px; font-weight: bold; border-bottom: 1px solid #dee2e6;">Umbral de Alerta:</td>
              <td style="padding: 8px; border-bottom: 1px solid #dee2e6;">${threshold} unidades</td>
            </tr>
          </table>
          <p>Por favor, reabastece este producto a la brevedad para evitar quiebres de inventario.</p>
        </div>
      `,
    });
  }
}
