package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Random numeroRandomico;

    //Atributos de configuracao

    private int larguraDispositivo;
    private int alturaDispositivo;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomica;

    @Override

    public void create() {

        batch = new SpriteBatch();
        numeroRandomico = new Random();
        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");

        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
        posicaoInicialVertical = alturaDispositivo / 2;
        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 300;

    }

    @Override

    public void render() {

        // += Variação recebe a propria variação e soma com o valor indicado a frente deste sinal.

        deltaTime = Gdx.graphics.getDeltaTime();

        variacao += deltaTime * 10;

        // -= Variação recebe a propria variação e subtrai com o valor indicado a frente deste sinal.

        posicaoMovimentoCanoHorizontal -= deltaTime * 200;
        velocidadeQueda++;

        if (variacao > 2) variacao = 0;

        if (Gdx.input.justTouched())
            velocidadeQueda = -15;


        if (posicaoInicialVertical > 0 || velocidadeQueda < 0)
            posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;

        // Verifica se o cano saiu totalmente da tela

        if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
            posicaoMovimentoCanoHorizontal = larguraDispositivo;

            // numeroRandomico.nextInt(i: 400) Sorteia numeros entre 0 e 400.

            alturaEntreCanosRandomica = numeroRandomico.nextInt(400) - 200;



        }

        batch.begin();

        // A ordem de declaracao das imagens tem relevancia. Porque indica qual eh a ordem de apresentacao das imagens na tela.

        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica);

        // (int) nomeDoAtributo Converte o atributo para inteiro

        batch.draw(passaros[(int) variacao], 120, posicaoInicialVertical);

        batch.end();

    }
}
