-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 12-Mar-2017 às 03:46
-- Versão do servidor: 5.5.54-0+deb8u1
-- PHP Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Biblioteca`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `autores`
--

CREATE TABLE IF NOT EXISTS `autores` (
`id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `nacionalidade` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `autores`
--

INSERT INTO `autores` (`id`, `nome`, `nacionalidade`) VALUES
(1, 'Rafael Gov', 'Brasileiro'),
(2, 'Rafael Java Script', 'Mexicano'),
(3, 'Pressman', 'Brasileiro'),
(4, 'Tanenbaum', 'Brasileiro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `editoras`
--

CREATE TABLE IF NOT EXISTS `editoras` (
`id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `editoras`
--

INSERT INTO `editoras` (`id`, `nome`, `cidade`) VALUES
(1, 'Rafael Escritor', 'Coxim'),
(2, 'Kleber Editor', 'INGLES'),
(3, 'Jose Editor', 'Sao Paulo'),
(4, 'Adriana Editor', 'Sao Gabriel'),
(5, 'Angelo Editor', 'Coxim');

-- --------------------------------------------------------

--
-- Estrutura da tabela `emprestimos`
--

CREATE TABLE IF NOT EXISTS `emprestimos` (
`id` int(11) NOT NULL,
  `data_emprestimo` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_ativo` tinyint(1) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_livro` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `emprestimos`
--

INSERT INTO `emprestimos` (`id`, `data_emprestimo`, `is_ativo`, `id_usuario`, `id_livro`) VALUES
(1, '2017-03-12 16:30:12', 1, 1, 1),
(2, '2017-03-20 22:42:07', 1, 5, 1),
(3, '2017-03-12 03:16:04', 0, 2, 1),
(4, '2017-03-12 03:16:18', 0, 4, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `enderecos`
--

CREATE TABLE IF NOT EXISTS `enderecos` (
`id` int(11) NOT NULL,
  `rua` varchar(45) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `s_n` tinyint(1) DEFAULT NULL,
  `complemento` varchar(45) DEFAULT '',
  `bairro` varchar(45) NOT NULL,
  `cep` varchar(45) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_municipio` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `enderecos`
--

INSERT INTO `enderecos` (`id`, `rua`, `numero`, `s_n`, `complemento`, `bairro`, `cep`, `id_usuario`, `id_municipio`) VALUES
(2, 'Salvina Maria Do Carmo', 233, 0, 'Ao Lado da publison', 'Flavio Garcia', '79400000', 4, 1),
(6, 'Rua Salvina Maria Do Carmo', 222, 0, '222', 'Falvio Garica', '794000', 2, 2),
(7, 'Salvina', 312, 0, '312', 'ruatr1', '23424', 5, 2),
(8, 'Rua Das Ovelhas', 1231, 1, '222', 'Pedro', '79400000', 6, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estudantes`
--

CREATE TABLE IF NOT EXISTS `estudantes` (
  `id` int(11) NOT NULL,
  `rga` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `estudantes`
--

INSERT INTO `estudantes` (`id`, `rga`) VALUES
(2, '31231333'),
(4, '312312312'),
(5, '31231321'),
(6, '3333333');

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros`
--

CREATE TABLE IF NOT EXISTS `livros` (
`id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `isbn` int(11) DEFAULT NULL,
  `edicao` int(11) DEFAULT NULL,
  `classificacao` varchar(45) DEFAULT NULL,
  `idioma` varchar(45) DEFAULT NULL,
  `ano_publi` date DEFAULT NULL,
  `id_editora` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livros`
--

INSERT INTO `livros` (`id`, `nome`, `isbn`, `edicao`, `classificacao`, `idioma`, `ano_publi`, `id_editora`) VALUES
(1, 'BDD - Segundo Kleber Kruger', 31231, 5, 'Cientifico', 'BRASILEIRO', '2017-09-22', 2),
(2, 'Rafael JS', 3331, 5, 'Cientifico', 'BRASILEIRO', '1995-09-22', 1),
(3, 'S.O - Segundo Klebler Kruger', 22312, 4, 'Cientifico', 'BRASILEIRO', '1994-09-22', 2),
(4, 'AN  - Segundo Jose', 323233, 2, 'Cientifico', 'BRASILEIRO', '2013-03-21', 3),
(5, 'Manual UFMS', 331131, 2, 'COLECAO', 'BRASILEIRO', '2017-03-01', 1),
(6, 'Coxim Programmer', 323233, 31231, 'CIENTIFICO', 'BRASILEIRO', '2017-03-02', 5),
(7, 'Rafael PHP', 31231, 4, 'Cientifico', 'BRASILEIRO', '2010-02-22', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros_has_autores`
--

CREATE TABLE IF NOT EXISTS `livros_has_autores` (
  `id_livro` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livros_has_autores`
--

INSERT INTO `livros_has_autores` (`id_livro`, `id_autor`) VALUES
(1, 1),
(2, 1),
(3, 1),
(7, 1),
(2, 2),
(3, 2),
(1, 3),
(4, 3),
(1, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `municipios`
--

CREATE TABLE IF NOT EXISTS `municipios` (
`id` int(11) NOT NULL,
  `ibge` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `UF` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `municipios`
--

INSERT INTO `municipios` (`id`, `ibge`, `nome`, `UF`) VALUES
(1, 1, 'COXIM', 'MS'),
(2, 2, 'Campo Grande', 'MS');

-- --------------------------------------------------------

--
-- Estrutura da tabela `professores`
--

CREATE TABLE IF NOT EXISTS `professores` (
  `id` int(11) NOT NULL,
  `siap` int(11) NOT NULL,
  `is_substituto` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `professores`
--

INSERT INTO `professores` (`id`, `siap`, `is_substituto`) VALUES
(1, 31231231, 1),
(8, 312312, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `telefones`
--

CREATE TABLE IF NOT EXISTS `telefones` (
`id` int(11) NOT NULL,
  `ddd` varchar(3) NOT NULL,
  `numero` varchar(11) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `is_principal` tinyint(1) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `telefones`
--

INSERT INTO `telefones` (`id`, `ddd`, `numero`, `tipo`, `is_principal`, `id_usuario`) VALUES
(1, '45', '6731231', 'Celular', 1, 2),
(2, '66', '999999', 'Comercial', 1, 2),
(3, '45', '99507979', 'Celular', 1, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
`id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `curso` varchar(45) DEFAULT NULL,
  `cpf` char(11) DEFAULT NULL,
  `titulacao` varchar(45) DEFAULT NULL,
  `data_inicio_contrato` date NOT NULL,
  `data_fim_contrato` date NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `data_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `nome`, `curso`, `cpf`, `titulacao`, `data_inicio_contrato`, `data_fim_contrato`, `data_nascimento`, `data_at`) VALUES
(1, 'Marcel Japa', 'SISTEMAS DE INFORMAÇÂO', '33133312333', 'Mestre', '1994-09-22', '2019-09-22', '1995-09-22', '2017-03-06 20:28:05'),
(2, 'Rafael G O V', 'SISTEMAS DE INFORMAÇÂO', '39032566172', 'Mestre', '1992-01-22', '2019-09-22', '1995-09-22', '2017-03-06 20:35:59'),
(4, 'Poll', 'SISTEMAS DE INFORMAÇÂO', '31231231231', 'Mestre', '2000-09-22', '2000-09-22', '1995-09-22', '2017-03-11 01:19:15'),
(5, 'Kleber Kruger', 'SISTEMAS DE INFORMCAO', '31233333309', 'MESTRE', '2014-02-11', '2080-07-11', '1988-03-22', '2017-03-12 01:10:21'),
(6, 'Higor Silva', 'SISTEMAS DE INFORMAÇÂO', '33333333333', 'Graduando', '2016-09-22', '2019-09-22', '1998-09-10', '2017-03-12 04:50:48'),
(8, 'Ekler', 'SISTEMAS DE INFORMAÇÂO', '31223332122', 'Mestre', '2000-09-22', '2019-09-22', '1955-09-22', '2017-03-12 06:43:03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autores`
--
ALTER TABLE `autores`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `editoras`
--
ALTER TABLE `editoras`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emprestimos`
--
ALTER TABLE `emprestimos`
 ADD PRIMARY KEY (`id`,`id_usuario`,`id_livro`), ADD KEY `fk_book_has_User_User1_idx` (`id_usuario`), ADD KEY `fk_book_has_User_book1_idx` (`id_livro`);

--
-- Indexes for table `enderecos`
--
ALTER TABLE `enderecos`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_Address_User1_idx` (`id_usuario`), ADD KEY `fk_Address_County1_idx` (`id_municipio`);

--
-- Indexes for table `estudantes`
--
ALTER TABLE `estudantes`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `livros`
--
ALTER TABLE `livros`
 ADD PRIMARY KEY (`id`,`id_editora`), ADD KEY `fk_livros_editoras1_idx` (`id_editora`);

--
-- Indexes for table `livros_has_autores`
--
ALTER TABLE `livros_has_autores`
 ADD PRIMARY KEY (`id_livro`,`id_autor`), ADD KEY `fk_livros_has_autores_autores1_idx` (`id_autor`), ADD KEY `fk_livros_has_autores_livros1_idx` (`id_livro`);

--
-- Indexes for table `municipios`
--
ALTER TABLE `municipios`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `professores`
--
ALTER TABLE `professores`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `telefones`
--
ALTER TABLE `telefones`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_Phone_User1_idx` (`id_usuario`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autores`
--
ALTER TABLE `autores`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `editoras`
--
ALTER TABLE `editoras`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `emprestimos`
--
ALTER TABLE `emprestimos`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `enderecos`
--
ALTER TABLE `enderecos`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `livros`
--
ALTER TABLE `livros`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `municipios`
--
ALTER TABLE `municipios`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `telefones`
--
ALTER TABLE `telefones`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `emprestimos`
--
ALTER TABLE `emprestimos`
ADD CONSTRAINT `fk_book_has_User_book1` FOREIGN KEY (`id_livro`) REFERENCES `livros` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_book_has_User_User1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `enderecos`
--
ALTER TABLE `enderecos`
ADD CONSTRAINT `fk_Address_County1` FOREIGN KEY (`id_municipio`) REFERENCES `municipios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_Address_User1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `estudantes`
--
ALTER TABLE `estudantes`
ADD CONSTRAINT `fk_estudantes_usuarios1` FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `livros`
--
ALTER TABLE `livros`
ADD CONSTRAINT `fk_livros_editoras1` FOREIGN KEY (`id_editora`) REFERENCES `editoras` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `livros_has_autores`
--
ALTER TABLE `livros_has_autores`
ADD CONSTRAINT `fk_livros_has_autores_autores1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_livros_has_autores_livros1` FOREIGN KEY (`id_livro`) REFERENCES `livros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `professores`
--
ALTER TABLE `professores`
ADD CONSTRAINT `fk_professores_usuarios1` FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `telefones`
--
ALTER TABLE `telefones`
ADD CONSTRAINT `fk_Phone_User1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
