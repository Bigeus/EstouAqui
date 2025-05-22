package com.example.estouaqui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mensagens.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MESSAGES = "mensagens";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MESSAGE = "mensagem";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_MESSAGES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MESSAGE + " TEXT NOT NULL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }

    public void initializeMessages() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Verificar se já existem mensagens no banco
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_MESSAGES, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            // Adicionar mensagens iniciais
            String[] messages = {
                    "Você é mais forte do que imagina. Cada pequeno passo conta na sua jornada.",
                    "Lembre-se que é normal ter dias difíceis. O importante é não desistir.",
                    "Sua presença no mundo faz diferença. Você importa muito mais do que imagina.",
                    "Respire fundo. Este momento vai passar e dias melhores virão.",
                    "Você merece amor, especialmente o amor próprio. Cuide-se com gentileza hoje.",

                    "Você é mais forte do que imagina. Acredite no seu potencial.",
                    "Sua existência torna o mundo mais bonito. Nunca duvide disso.",
                    "Cada dia é uma nova chance de recomeçar. Você consegue!",
                    "Você é uma pessoa única e especial. Não há ninguém igual a você.",
                    "Seus erros não te definem. Eles são apenas degraus para seu crescimento.",
                    "Você merece amor, respeito e felicidade. Comece por se dar isso.",
                    "Ninguém pode fazer você se sentir inferior sem seu consentimento.",
                    "Você é capaz de coisas incríveis. Basta dar o primeiro passo.",
                    "Sua jornada é importante, mesmo nos dias difíceis.",
                    "Você é mais corajoso do que pensa, mais forte do que parece e mais inteligente do que acredita.",
                    "Permita-se ser feliz hoje, sem esperar condições perfeitas.",
                    "Seu valor não diminui porque alguém não soube reconhecê-lo.",
                    "Você já superou tantos desafios. Esse também vai passar.",
                    "A vida te deu asas. Voar é sua escolha.",
                    "Você não está sozinho(a). Muitas pessoas se importam com você.",
                    "Seu sorriso pode iluminar o dia de alguém. Inclusive o seu próprio.",
                    "Nunca subestime o poder que você tem de transformar sua vida.",
                    "Você é a melhor versão de si mesmo quando escolhe ser feliz.",
                    "Cada pequeno progresso é motivo para comemorar.",
                    "Você não precisa ser perfeito(a), apenas precisa ser você mesmo(a).",
                    "Seu coração é resistente. Ele sabe como se recuperar.",
                    "Acredite na beleza da sua jornada, mesmo nos dias difíceis.",
                    "Você é um presente para o mundo. Nunca se esqueça disso.",
                    "Sua luz interior é poderosa. Deixe-a brilhar.",
                    "Você merece todo o amor que guarda para dar aos outros.",
                    "Nenhuma tempestade dura para sempre. O sol vai brilhar novamente.",
                    "Você é suficiente, exatamente como é hoje.",
                    "Seu caminho é único. Não o compare com o dos outros.",
                    "A esperança é como o sol - se você só acreditar nela quando a vê, nunca passará pela noite.",
                    "Você é mais resiliente do que imagina. Já provou isso muitas vezes.",
                    "Respire fundo. Este momento difícil vai passar.",
                    "A dor que você sente hoje é a força que sentirá amanhã.",
                    "Nenhuma noite é tão escura que não termine com o amanhecer.",
                    "Você já sobreviveu a 100% dos seus dias difíceis até agora.",
                    "A esperança é o pilar que sustenta o mundo.",
                    "Mesmo pequenos progressos ainda são progressos.",
                    "A tempestade não dura para sempre. Mantenha-se firme.",
                    "Você não está sozinho(a) nesta jornada. Muitos se importam.",
                    "A cura vem com o tempo. Dê esse tempo a si mesmo.",
                    "Sua história ainda está sendo escrita. Não desista agora.",
                    "A vida te surpreenderá positivamente quando você menos esperar.",
                    "Você é mais forte do que qualquer obstáculo que apareça.",
                    "Permita-se sentir, mas não deixe a dor definir você.",
                    "Novos começos muitas vezes se disfarçam de finais dolorosos.",
                    "A cada dia que passa, você fica mais perto da luz.",
                    "Sua força interior é maior do que qualquer desafio.",
                    "Você não está preso(a) no passado. Você está crescendo a partir dele.",
                    "A vida é como um arco-íris - precisa de chuva e sol para aparecer.",
                    "Nenhum sentimento é final. Tudo passa e se transforma.",
                    "Você tem dentro de si tudo o que precisa para ser feliz.",
                    "A escuridão não pode apagar sua luz interior.",
                    "Seu futuro tem coisas boas que você nem imagina ainda.",
                    "Você é um guerreiro(a) silencioso(a). Continue lutando.",
                    "A vida te deu este desafio porque sabe que você pode superá-lo.",
                    "Você não está quebrado(a), apenas em reconstrução.",
                    "A cura não é linear. Cada pequeno passo conta.",
                    "Você já sobreviveu a todos os seus dias mais difíceis. Isso é força.",
                    "A esperança é a última que morre porque sabe que a vida sempre renasce.",
                    "Você está crescendo, mesmo quando não percebe.",
                    "Nenhuma dor é eterna. A alegria voltará a bater à sua porta.",

                    "Está tudo bem não estar bem o tempo todo.",
                    "Permita-se descansar. Você merece uma pausa.",
                    "Não carregue o mundo nas costas. Compartilhe o peso.",
                    "Você não precisa ser forte o tempo todo. Está ok pedir ajuda.",
                    "Seus sentimentos são válidos, mesmo os mais difíceis.",
                    "Chorar não é sinal de fraqueza, mas de coragem para sentir.",
                    "Você não está sozinho(a). Estou aqui por você.",
                    "Algumas feridas precisam de tempo, não de pressa.",
                    "Não há vergonha em recuar para depois avançar com mais força.",
                    "Você é amado(a) mais do que imagina.",
                    "Não existe maneira certa ou errada de lidar com a dor.",
                    "Você não precisa enfrentar tudo sozinho(a). Peça ajuda.",
                    "Seu valor não diminui nos dias em que você se sente fraco(a).",
                    "Permita-se sentir sem julgamentos. Você está humano(a).",
                    "A dor que você sente hoje não será a mesma de amanhã.",
                    "Você está fazendo o melhor que pode, e isso é suficiente.",
                    "Algumas batalhas são silenciosas, mas não menos importantes.",
                    "Você não está falhando, apenas aprendendo a voar.",
                    "Seu coração sabe curar-se. Dê tempo a ele.",
                    "A escuridão não define quem você é, apenas onde você está agora.",

                    "Hoje é um novo dia cheio de novas possibilidades.",
                    "Você é o autor(a) da sua história. Que capítulo vai escrever hoje?",
                    "Pequenos passos ainda te levam adiante.",
                    "A vida é 10% do que acontece e 90% como você reage.",
                    "Você não precisa ver toda a escada, apenas dar o primeiro passo.",
                    "Cada manhã é uma página em branco. Escreva uma história bonita.",
                    "Sorria para o espelho. Você merece esse gesto de amor.",
                    "Faça algo hoje que seu eu do futuro agradecerá.",
                    "Você é mais corajoso(a) do que pensa. Acredite!",
                    "Nenhum mar foi navegado por quem ficou apenas olhando as ondas.",
                    "Sua vida é sua obra de arte. Pinte-a com cores vibrantes.",
                    "O sol nasce todos os dias. Com ele, novas oportunidades.",
                    "Você tem dentro de si tudo o que precisa para ser feliz hoje.",
                    "Não espere condições perfeitas. Aja com o que você tem agora.",
                    "Cada dia é um presente não aberto. Desembrulhe-o com esperança.",
                    "Você é o milagre que estava esperando.",
                    "A vida te deu este dia. Use-o bem.",
                    "Grandes coisas começam com pequenas atitudes.",
                    "Seu potencial é ilimitado. Explore-o!",
                    "O mundo precisa do seu brilho único. Não o esconda.",

                    "Você é capaz de coisas extraordinárias,",
                    "Sua luz interior merece brilhar,",
                    "Ninguém pode te definir melhor que você mesmo,",
                    "Você é uma obra de arte em constante evolução,",
                    "Seu valor não diminui nos dias difíceis,",
                    "Você é suficiente exatamente como está,",
                    "Cada parte de você merece amor,",
                    "Sua jornada é única e valiosa,",
                    "Você é mais resiliente do que imagina,",
                    "Seus sonhos são válidos e alcançáveis,",
                    "Você merece todo o amor que dá aos outros,",
                    "Sua presença faz diferença no mundo,",
                    "Você está crescendo mesmo quando não percebe,",
                    "Seu coração sabe o caminho, confie nele,",
                    "Você é mais forte do que qualquer desafio,",
                    "Nada pode diminuir seu verdadeiro valor,",
                    "Você é digno de felicidade plena,",
                    "Seus erros não te definem, te fortalecem,",
                    "Você merece dias tão bonitos quanto sua alma,",
                    "Sua voz importa e merece ser ouvida,",
                    "Você é a melhor versão de si mesmo hoje,",
                    "Nunca subestime seu poder de recomeçar,",
                    "Você é uma combinação única de talentos,",
                    "Seu potencial é ilimitado,",
                    "Você merece comemorar cada pequena vitória,",

                    "Tudo passa, inclusive os dias difíceis,",
                    "A tempestade não apaga seu brilho,",
                    "Você já sobreviveu a 100% dos seus piores dias,",
                    "A esperança é o oxigênio da alma,",
                    "Nenhuma noite dura para sempre,",
                    "A cura vem quando menos se espera,",
                    "Você não está sozinho nesta caminhada,",
                    "A dor de hoje será força amanhã,",
                    "Novos começos surgem após cada fim,",
                    "Você é mais corajoso do que imagina,",
                    "A vida reserva surpresas maravilhosas para você,",
                    "Nenhum sentimento é permanente,",
                    "Você está mais perto da luz do que pensa,",
                    "A esperança é a última que morre porque sabe renascer,",
                    "Você está escrevendo uma história de superação,",
                    "A dor não define quem você é,",
                    "Você merece dias melhores e eles virão,",
                    "Nenhum obstáculo é maior que sua força interior,",
                    "A vida te surpreenderá positivamente,",
                    "Você está mais forte do que no início desta jornada,",
                    "A calmaria vem após toda tempestade,",
                    "Você não está quebrado, apenas em transformação,",
                    "A esperança é como as estrelas - sempre presente,",
                    "Você já venceu batalhas que nem lembra mais,",
                    "A vida te ama e quer te ver feliz,",
                    "Está tudo bem não estar bem o tempo todo,",

                    "Permita-se sentir sem julgamentos,",
                    "Você não precisa carregar o mundo sozinho,",
                    "Pedir ajuda é demonstração de força,",
                    "Seus sentimentos são válidos e importantes,",
                    "Você merece pausas e autocuidado,",
                    "Não há vergonha em precisar de apoio,",
                    "Você é amado mais do que imagina,",
                    "Algumas feridas precisam de tempo para cicatrizar,",
                    "Você não está falhando, está aprendendo,",
                    "Permita-se recomeçar quantas vezes precisar,",

                    "Você não precisa ter todas as respostas agora,",
                    "Seu caminho é único e especial,",
                    "Você merece compreensão e acolhimento,",
                    "Ninguém é forte o tempo todo,",
                    "Você está fazendo o melhor que pode,",
                    "Seu progresso não precisa ser linear,",
                    "Você merece gentileza, especialmente a sua própria,",
                    "Algumas batalhas são silenciosas mas importantes,",
                    "Você não está sozinho nos dias difíceis,",
                    "Permita-se descansar sem culpa,",
                    "Você é importante para muitas pessoas,",
                    "Seu valor não diminui nos dias de luta,",
                    "Você merece paz interior,",
                    "A ajuda está disponível quando precisar,",

                    "Hoje é um novo capítulo da sua história,",
                    "Pequenos passos ainda levam a grandes mudanças,",
                    "Você é o autor da sua própria vida,",
                    "Cada manhã traz novas oportunidades,",
                    "Sorria para o espelho, você merece,",
                    "Faça algo hoje que seu eu futuro agradecerá,",
                    "Você tem poder para transformar seu dia,",
                    "A vida é feita de momentos simples e belos,",
                    "Permita-se viver o presente plenamente,",
                    "Você cria sua própria luz,",
                    "O sol nasce para todos, inclusive para você,",
                    "Seu potencial está esperando para ser explorado,",
                    "Nenhum sonho é pequeno demais,",
                    "Você merece dias coloridos e leves,",
                    "A gratidão transforma o ordinário em extraordinário,",
                    "Você é capaz de escrever uma linda história hoje,",
                    "O mundo precisa do seu jeito único de ser,",
                    "Permita-se sonhar grande,",
                    "Você está mais próximo dos seus objetivos do que imagina,",
                    "Cada dia é uma página em branco para preencher,",
                    "Você merece momentos de pura felicidade,",
                    "A vida te oferece infinitas possibilidades,",
                    "Seu coração sabe o caminho da alegria,",
                    "Você é a mudança que quer ver no mundo,",
                    "O melhor de você está por vir,",

                    "A luz sempre encontra um caminho, mesmo nas noites mais escuras,",
                    "Você já carregou pesos que achava impossível levantar, e aqui está você,",
                    "Nenhuma tempestade dura para sempre, a calmaria vem,",
                    "Seus pés já caminharam por terrenos difíceis antes e continuam seguindo,",
                    "A esperança é como semente - pequena, mas capaz de romper o asfalto,",
                    "Você não está preso na escuridão, apenas de passagem por ela,",
                    "Cada respiração é uma prova de sua incrível resistência,",
                    "A vida te teceu com fios de resistência e coragem,",
                    "Nenhuma dor é eterna, mesmo quando parece sem fim,",
                    "Você é como o bambu - flexível, mas impossível de quebrar,",
                    "Seus olhos ainda brilham, mesmo depois de todas as lágrimas,",
                    "A força mora em você, mesmo quando não consegue senti-la,",
                    "Você é um sobrevivente, não uma vítima,",
                    "Nenhum inverno é tão longo que não ceda à primavera,",
                    "Você está sendo moldado, não destruído,",
                    "A cura acontece mesmo quando você não percebe,",
                    "Você já atravessou desertos emocionais antes e encontrará oásis novamente,",
                    "Sua história não termina aqui, há capítulos mais leves por vir,",
                    "A dor não é sua morada, apenas uma visita passageira,",
                    "Você está aprendendo a dançar na chuva, mesmo querendo sol,",
                    "Nenhuma noite é tão escura que você não veja ao menos uma estrela,",
                    "Você está se tornando, não se acabando,",
                    "A vida te prepara através dos desafios que te fortalecem,",
                    "Você é como o mar - às vezes calmo, às vezes agitado, mas sempre belo,",
                    "Nenhuma montanha é alta demais para seu espírito escalador,",
                    "Você está florescendo em seu próprio tempo,",
                    "A esperança é seu direito inalienável,",
                    "Você não está perdido, apenas se reencontrando,",
                    "Nenhuma cicatriz tira sua beleza, apenas conta sua história,",
                    "Você é como o sol - às vezes encoberto, mas sempre presente,",
                    "A dor é temporária, sua força é permanente,",
                    "Você está sendo preparado para algo maior,",
                    "Nenhuma derrota é definitiva enquanto você respira,",
                    "Você é um milagre em constante evolução,",
                    "A vida te surpreenderá positivamente em breve,",
                    "Você não está regredindo, apenas se reposicionando,",
                    "Nenhuma tristeza pode apagar sua luz interior,",
                    "Você está mais próximo da virada do que imagina,",
                    "A esperança é seu superpoder secreto,",
                    "Você é como a lua - mesmo na fase escura, está inteiro,",
                    "Nenhum problema é maior que sua capacidade de superação,",
                    "Você está escrevendo um final feliz para sua história,",
                    "A força aparece quando você menos espera,",
                    "Você não está sozinho, o universo conspira a seu favor,",
                    "Nenhuma queda é fatal enquanto você se levanta,",
                    "Você está sendo lapidado, não destruído,",
                    "A luz no fim do túnel não é ilusão,",
                    "Você é mais valente do que todos os seus medos juntos,",
                    "Nenhuma angústia resiste ao seu poder de transformação,",
                    "Você está plantando sementes que florescerão em breve,",
                    "A calmaria vem para quem persiste,",
                    "Você não está errando, está aprendendo o caminho,",
                    "Nenhuma nuvem fica para sempre no céu,",
                    "Você está se tornando sua melhor versão,",
                    "A esperança é a música que sua alma canta mesmo no silêncio,",
                    "Você é como o diamante - formado sob pressão,",
                    "Nenhum obstáculo resiste à sua determinação,",
                    "Você está mais forte do que no início dessa jornada,",
                    "A vida te reserva momentos de pura alegria,",
                    "Você não está parado, apenas se preparando para o próximo passo,",

                    "Você é mais forte do que qualquer desafio que a vida apresentar,",
                    "Sua jornada é importante e cada passo conta,",
                    "Você merece amor, respeito e felicidade em abundância,",
                    "Ninguém pode fazer você se sentir pequeno sem seu consentimento,",
                    "Você é uma pessoa única, com dons que só você pode oferecer ao mundo,",
                    "Seus erros não definem quem você é, apenas mostram que está tentando,",
                    "Você é mais corajoso do que imagina, mais forte do que parece,",
                    "Sua luz interior é poderosa, mesmo nos dias mais escuros,",
                    "Você não precisa ser perfeito, apenas precisa ser você mesmo,",
                    "Cada parte de você merece aceitação e carinho,",
                    "Você é suficiente exatamente como está neste momento,",
                    "Seu valor não diminui nos dias em que você se sente fraco,",
                    "Você merece dias tão bonitos quanto seu coração,",
                    "Sua presença no mundo faz diferença, mesmo quando você não percebe,",
                    "Você está crescendo mesmo quando não consegue ver o progresso,",
                    "Nada pode diminuir seu verdadeiro valor como ser humano,",
                    "Você é digno de todo o amor que recebe e de todo o que dá,",
                    "Seus sonhos são válidos e merecem ser perseguidos,",
                    "Você já superou tantos desafios - esse também vai passar,",
                    "Sua voz importa e merece ser ouvida,",
                    "Você é a melhor versão de si mesmo hoje, e amanhã será ainda melhor,",
                    "Nunca subestime seu poder de recomeçar quantas vezes for necessário,",
                    "Você é uma combinação única de talentos e qualidades especiais,",
                    "Seu potencial é ilimitado - explore-o com coragem,",
                    "Você merece comemorar cada pequena vitória ao longo do caminho,",
                    "Você não está sozinho, muitas pessoas se importam com você,",
                    "Seu sorriso pode iluminar o dia de alguém - inclusive o seu próprio,",
                    "Você é um presente para o mundo, exatamente como é,",
                    "Nenhuma tempestade pode apagar seu brilho interior,",
                    "Você é mais resiliente do que imagina - já provou isso muitas vezes,",

                    "Respire fundo - este momento difícil vai passar,",
                    "A dor que você sente hoje será a força que sentirá amanhã,",
                    "Nenhuma noite é tão escura que não termine com o amanhecer,",
                    "Você já sobreviveu a 100% dos seus dias difíceis até agora,",
                    "A esperança é como um farol - mesmo pequena, ilumina o caminho,",
                    "Mesmo pequenos progressos ainda são progressos,",
                    "A tempestade não dura para sempre - mantenha-se firme,",
                    "Você não está sozinho nesta jornada,",
                    "A cura vem com o tempo - dê esse tempo a si mesmo,",
                    "Sua história ainda está sendo escrita - não desista agora,",
                    "A vida te surpreenderá positivamente quando você menos esperar,",
                    "Você é mais forte do que qualquer obstáculo,",
                    "Permita-se sentir, mas não deixe a dor definir você,",
                    "Novos começos muitas vezes se disfarçam de finais dolorosos,",
                    "A cada dia que passa, você fica mais perto da luz,",
                    "Sua força interior é maior do que qualquer desafio,",
                    "Você não está preso no passado - está crescendo a partir dele,",
                    "A vida é como um arco-íris - precisa de chuva e sol para aparecer,",
                    "Nenhum sentimento é final - tudo passa e se transforma,",
                    "Você tem dentro de si tudo o que precisa para ser feliz,",
                    "A escuridão não pode apagar sua luz interior,",
                    "Seu futuro tem coisas boas que você nem imagina ainda,",
                    "Você é um guerreiro silencioso - continue lutando,",
                    "A vida te deu este desafio porque sabe que você pode superá-lo,",
                    "Você não está quebrado, apenas em reconstrução,",
                    "A cura não é linear - cada pequeno passo conta,",
                    "Você já sobreviveu a todos os seus dias mais difíceis - isso é força,",
                    "A esperança sabe que a vida sempre renasce,",
                    "Você está crescendo, mesmo quando não percebe,",
                    "Nenhuma dor é eterna - a alegria voltará a bater à sua porta,",
                    "A luz sempre encontra um caminho, mesmo nas noites mais escuras,",
                    "Você já carregou pesos que achava impossível levantar - e aqui está você,",
                    "Nenhuma tempestade dura para sempre - a calmaria vem,",
                    "Seus pés já caminharam por terrenos difíceis antes e continuam seguindo,",
                    "A esperança é como semente - pequena, mas capaz de romper o asfalto,",
                    "Você não está preso na escuridão, apenas de passagem por ela,",
                    "Cada respiração é uma prova de sua incrível resistência,",
                    "A vida te teceu com fios de resistência e coragem,",
                    "Nenhuma dor é eterna, mesmo quando parece sem fim,",
                    "Você é como o bambu - flexível, mas impossível de quebrar,",

                    "Está tudo bem não estar bem o tempo todo,",
                    "Permita-se descansar - você merece uma pausa,",
                    "Não carregue o mundo nas costas - compartilhe o peso,",
                    "Você não precisa ser forte o tempo todo - está ok pedir ajuda,",
                    "Seus sentimentos são válidos, mesmo os mais difíceis,",
                    "Chorar não é sinal de fraqueza, mas de coragem para sentir,",
                    "Você não está sozinho - estou aqui por você,",
                    "Algumas feridas precisam de tempo, não de pressa,",
                    "Não há vergonha em recuar para depois avançar com mais força,",
                    "Você é amado mais do que imagina,",
                    "Não existe maneira certa ou errada de lidar com a dor,",
                    "Você não precisa enfrentar tudo sozinho - peça ajuda,",
                    "Seu valor não diminui nos dias em que você se sente fraco,",
                    "Permita-se sentir sem julgamentos - você está humano,",
                    "A dor que você sente hoje não será a mesma de amanhã,",
                    "Você está fazendo o melhor que pode, e isso é suficiente,",
                    "Algumas batalhas são silenciosas, mas não menos importantes,",
                    "Você não está falhando, apenas aprendendo a voar,",
                    "Seu coração sabe curar-se - dê tempo a ele,",
                    "A escuridão não define quem você é, apenas onde você está agora,",
                    "Está tudo bem em não ter todas as respostas agora,",
                    "Você merece gentileza, especialmente a sua própria,",
                    "Ninguém é forte o tempo todo - permita-se momentos de fragilidade,",
                    "Seu progresso não precisa ser linear - cada um tem seu ritmo,",
                    "Você é importante para muitas pessoas, mesmo que não perceba,",
                    "Permita-se recomeçar quantas vezes for necessário,",
                    "Você não está regredindo, apenas se reposicionando,",
                    "A ajuda está disponível quando precisar - não hesite em buscar,",
                    "Você merece paz interior e dias mais leves,",
                    "A vida tem mais beleza reservada para você - continue caminhando,",
            };

            for (String message : messages) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_MESSAGE, message);
                db.insert(TABLE_MESSAGES, null, values);
            }
        }
    }

    public String getRandomMessage() {
        SQLiteDatabase db = this.getReadableDatabase();
        String message = "Você é incrível!"; // Mensagem padrão

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_MESSAGE + " FROM " + TABLE_MESSAGES, null);

        if (cursor.moveToFirst()) {
            List<String> messages = new ArrayList<>();
            do {
                messages.add(cursor.getString(0));
            } while (cursor.moveToNext());

            Random random = new Random();
            message = messages.get(random.nextInt(messages.size()));
        }

        cursor.close();
        return message;
    }
}