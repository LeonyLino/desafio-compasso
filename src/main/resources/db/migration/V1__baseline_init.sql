CREATE TABLE tb_produto (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(100) NOT NULL,
	price NUMERIC NOT NULL
);

INSERT INTO tb_produto VALUES
	(DEFAULT, 'Monitor LG Gamer', 'UltraWide 25" IPS Full HD 1ms MBR 25UM58G', '902.41'),
	(DEFAULT, 'Echo Dot (3ª Geração)', 'Smart Speaker com Alexa - Cor Preta', '331.55'),
	(DEFAULT, 'Echo Show 5', 'Smart Speaker com tela de 5,5" e Alexa - Cor Preta', '569.05'),
	(DEFAULT, 'Echo Studio', 'Smart Speaker com áudio de alta fidelidade e Alexa', '1614.05'),
	(DEFAULT, 'Novo Echo (4ª Geração)', 'Com som premium, hub de casa inteligente e Alexa - Cor Preta', '711.55'),
	(DEFAULT, 'Mouse Gamer Logitech G203', 'Lightsync Rgb, Efeito de Ondas de Cores, 6 Botões Programáveis e Até 8.000 DPI - Lilás', '149.90');
