import { PrismaClient } from '@prisma/client';
import * as bcrypt from 'bcryptjs';

const prisma = new PrismaClient();

async function main() {
  console.log('Connecting to database...');
  const user = await prisma.usuarios.findFirst({
    where: { email: 'pablo@gmail.com' }
  });

  if (user) {
    console.log(`User found: ${user.email}`);
    console.log(`Password hash in DB: ${user.password}`);
    const isMatch = await bcrypt.compare('123456', user.password);
    console.log(`Does '123456' match? ${isMatch}`);

    if (!isMatch) {
      console.log('Password does not match. Updating to bcrypt hash of "123456"...');
      const newHash = await bcrypt.hash('123456', 10);
      await prisma.usuarios.update({
        where: { id: user.id },
        data: { password: newHash }
      });
      console.log('Password updated successfully.');
    }
  } else {
    console.log('User pablo@gmail.com not found!');
  }
}

main()
  .catch((e) => {
    console.error('Error running script:', e);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
