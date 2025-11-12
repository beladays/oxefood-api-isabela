package br.com.ifpe.oxefood.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class Util {

    public static final String LOCAL_ARMAZENAMENTO_IMAGENS = "\"C:\\Users\\Aluno\\Desktop\\web4-isa\\oxefood-web-isabela\\public\""; //caminho pasta de imgs

    public static String fazerUploadImagem(MultipartFile imagem) {

        boolean sucessoUpload = false;
        String nomeArquivoComDataHora = null;
//recebe img, verifica se ta vazio, escolhe o nome do aqruivo e coloca na variavel e concatena a data e hora do momento q fez o upload do arquivo p nome ser unico
        if (imagem != null && !imagem.isEmpty()) {

            String nomeOriginalArquivo = imagem.getOriginalFilename();
            nomeArquivoComDataHora = LocalDateTime.now().toString() + " - " + nomeOriginalArquivo;

            try {

                // Criando o diretório para armazenar o arquivo
                
                String imagens_projetos = LOCAL_ARMAZENAMENTO_IMAGENS + "/imagens_cadastradas";
                File dir = new File(imagens_projetos);
    if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Criando o arquivo no diretório
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivoComDataHora);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(imagem.getBytes());
                stream.close();

                System.out.println("Arquivo armazenado em:" + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo " + nomeOriginalArquivo + " com sucesso");
                sucessoUpload = true;

            } catch (Exception e) {
                System.out.println("Você falhou em carregar o arquivo " + nomeOriginalArquivo + " => " + e.getMessage());
            }

        } else {
            System.out.println("Você falhou em carregar o arquivo porque ele está vazio ");
        }

        if (sucessoUpload) {
            return nomeArquivoComDataHora;
        } else {
            return null;
        }
    }

}
