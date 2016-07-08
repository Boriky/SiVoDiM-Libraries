package starklabs.sivodim.Drama.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import starklabs.sivodim.R;

public class UserManualActivity extends AppCompatActivity implements UserManualInterface{

    private TextView userManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        getSupportActionBar().setTitle("Manuale utente");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userManual=(TextView)findViewById(R.id.userManual);
        userManual.setText(Html.fromHtml("<h1>Manuale utente</h1>\n" +
                "<h2>1 Introduzione</h2>\n" +
                "<h3>1.1 Scopo del documento</h3>\n" +
                "<p>Questo documento rappresenta il manuale d’uso indirizzato all’utente finale delle applicazioni\n" +
                "Speeches e SiVoDiM, sviluppate per la realizzazione del progetto universitario SiVoDiM:\n" +
                "Sintesi Vocale per Dispositivi Mobili. Il documento è disponibile direttamente dal drawer\n" +
                "delle app e ha lo scopo di descrivere le modalità d’utilizzo di tutte le funzionalità offerte,\n" +
                "suddivise in opportune sezioni.</p>\n" +
                "\n" +
                "<h3>1.2 Scopo del prodotto</h3>\n" +
                "<p>Il prodotto si pone l’obiettivo di dimostrare efficacemente le potenzialità del motore di sintesi\n" +
                "vocale FA-TTS, realizzato dall’azienda MIVOQ s.r.l. e messo a disposizione del team di\n" +
                "sviluppo Stark Labs. Il team rende disponibili due applicazioni per sistemi Android:</p>\n" +
                "<ul>\n" +
                "<li>Speeches: permette la creazione e il salvataggio di racconti e sceneggiati, che possono\n" +
                "essere esportati in formato audio (o video) attraverso l’utilizzo del motore FA-TTS;</li>\n" +
                "<li>SiVoDiM: permette all’utente di interfacciarsi direttamente con il sistema operativo\n" +
                "per configurare, salvare e modficare le voci ereditate dal motore di sintesi FA-TTS di\n" +
                "MIVOQ.</li>\n" +
                "</ul>\n" +
                "<p>Entrambe le applicazioni si interfacciano con un modulo di basso livello:</p>\n" +
                "<ul><li>Modulo di sistema: permette di interfacciarsi tramite connessione di rete al motore\n" +
                "FA-TTS. Fornisce una libreria contenente tutte le funzionalità offerte da FA-TTS, utile\n" +
                "nell’ottica di un riuso futuro del software.</li></ul>\n" +
                "<p>La realizzazione delle suddette componenti è stata svolta a carico del team di sviluppo Stark\n" +
                "Labs.</p>\n" +
                "\n" +
                "<h2>2 Guida all’installazione</h2>\n" +
                "<h3>2.1 Requisiti di sistema</h3>\n" +
                "<p>Per poter utilizzare SiVoDiM è necessario disporre di un dispositivo smartphone equipaggiato\n" +
                "con sistema operativo Android 4.0 o superiore.</p>\n" +
                "<h3>2.2 Installazione</h3>\n" +
                "<p>L’applicazione non è ancora disponibile nella piattaforma Google Play. Pertanto è necessario\n" +
                "installarla manualmente seguendo la procedura che segue:</p>\n" +
                "<ol>\n" +
                "<li>Scaricare l’apk dalla pagina ufficiale del team di sviluppo;</li>\n" +
                "<li>Salvare l’apk nella memoria SD del dispositivo;</li>\n" +
                "<li>Aprire le Impostazioni del dispositivo ed entrare nel menu Sicurezza;</li>\n" +
                "<li>Scorrere il menu e abilitare la voce Origini sconosciute;</li>\n" +
                "<li>Selezionare l’apk dalla memoria del dispositivo:\n" +
                "<ul><li>Se l’applicazione è stata scaricata direttamente dal dispositivo, l’apk si può\n" +
                "eseguire dalla cartella Download (opzione di default);</li>\n" +
                "<li>Se l’applicazione è stata scaricata da PC, è necessario trasferirla tramite connessione\n" +
                "USB al dispositivo e disporre di un gestore file per navigare nella cartella\n" +
                "in cui è stata copiata.</li></ul></li>\n" +
                "<li>Seguire la procedura guidata di Android per completare il processo di installazione.</li></ol>\n" +
                "\n" +
                "<h2>3 Guida alle funzionalità di Speeches</h2>\n" +
                "<h3>3.1 Navigare fra le schermate principali</h3>\n" +
                "<p>Avviata l’applicazione è possibile accedere a uno sceneggiato (al primo avvio è disponibile\n" +
                "uno sceneggiato di prova). Nell’angolo in alto a sinistra è presente il pulsante di accesso al\n" +
                "drawer. Dal drawer è possibile accedere all’applicazione di configurazione SiVoDiM,\n" +
                "al manuale utente o alle info dell’app.\n" +
                "All’interno di uno sceneggiato si possono visualizzare i capitoli interni. Nella barra degli\n" +
                "strumenti è possibile esportare rapidamente lo sceneggiato in formato MP3 e condividerlo\n" +
                "su altre applicazioni attraverso la pressione delle rispettive icone. Nell’angolo in alto a destra\n" +
                "è presente il pulsante di accesso al menu delle opzioni, che rende disponibili le seguenti\n" +
                "voci:</p>\n" +
                "<ul>\n" +
                "<li>Salva progetto: salva una copia locale dello sceneggiato aperto;</li>\n" +
                "<li>Esporta in video: esporta lo sceneggiato in locale nel formato MP4 (video);</li>\n" +
                "<li>Condividi video: condividi lo sceneggiato esportato in video su social network o\n" +
                "applicazioni di messaggistica;</li>\n" +
                "<li>Crea personaggio: apre la schermata dedicata alla creazione di un nuovo personaggio;</li>\n" +
                "<li>Lista personaggi: accesso alla lista completa di tutti i personaggi appartenenti allo\n" +
                "sceneggiato aperto.</li></ul>\n" +
                "<p>All’interno del capitolo si possono visualizzare le battute pronunciate dai personaggi.\n" +
                "Nella barra degli strumenti è possibile aggiungere rapidamente un nuovo personaggio attraverso\n" +
                "la pressione della rispettiva icona. Nell’angolo in alto a destra è presente il pulsante\n" +
                "di accesso al menu delle opzioni ( ), che rende disponibili le seguenti voci:</p>\n" +
                "<ul>\n" +
                "<li>Modifica capitolo: abilita la modifica di titolo, soundtrack\n" +
                " e immagine di sfondo;</li>\n" +
                "<li>Lista personaggi: accesso alla lista completa di tutti i personaggi appartenenti allo\n" +
                "sceneggiato aperto.</li></ul>\n" +
                "<h3>3.2 Creare un nuovo sceneggiato</h3>\n" +
                "<p>Avviata l’applicazione si potrà sin da subito creare nuovi sceneggiati. Per creare un nuovo\n" +
                "progetto è necessario:</p>\n" +
                "<ol>\n" +
                "<li>Cliccare sul pulsante \"+\" ( ) posto nell’angolo in basso a destra dello schermo per\n" +
                "avviare la schermata di creazione;</li>\n" +
                "<li>Assegnare un nome allo sceneggiato;</li>\n" +
                "<li>Importare i personaggi da uno sceneggiato già creato (opzionale):\n" +
                "(a) Selezionare dal menu a tendina lo sceneggiato da cui si vogliono importare i\n" +
                "personaggi.</li>\n" +
                "<li>Confermare la creazione premendo l’apposita icona posta nell’angolo in alto a destra\n" +
                "dello schermo.</li></ol>\n" +
                "<p>A questo punto lo sceneggiato è disponibile nella home.\n" +
                "Premendo il pulsante che visualizza il nome dello sceneggiato, è possibile navigare all’interno\n" +
                "di esso e iniziare a popolarlo con nuovi capitoli e rispettive battute.</p>\n" +
                "<h3>3.3 Creare un nuovo capitolo<h3>\n" +
                "<p>Entrati nella schermata interna allo sceneggiato è possibile aggiungere nuovi capitoli. Per\n" +
                "creare un nuovo capitolo è necessario:</p>\n" +
                "<ol>\n" +
                "<li>Cliccare sul pulsante \"+\" ( ) posto nell’angolo in basso a destra dello schermo per\n" +
                "avviare la schermata di creazione;</li>\n" +
                "<li>Assegnare un nome al capitolo;</li>\n" +
                "<li>Premere il pulsante CARICA SOUNDTRACK e caricare una traccia audio che funge\n" +
                "da sottofondo sonoro al capitolo (opzionale):\n" +
                "(a) Navigare fra i file presenti nella memoria SD e selezionare la traccia audio\n" +
                "desiderata (formati supportati: MP3 e WAV).</li>\n" +
                "<li>Premere il pulsante CARICA SFONDO e caricare un’immagine che funge da sfondo al\n" +
                "capitolo (opzionale):\n" +
                "(a) Navigare fra i file presenti nella galleria del dispositivo e selezionare l’immagine\n" +
                "desiderata (formati supportati: JPG e PNG).</li>\n" +
                "<li>Confermare la creazione premendo l’apposita icona posta nell’angolo in alto a destra\n" +
                "dello schermo.</li></ol>\n" +
                "<p>A questo punto il capitolo è disponibile nella schermata interna allo sceneggiato.\n" +
                "Premendo il pulsante che visualizza il nome del capitolo, è possibile navigare all’interno di\n" +
                "esso e iniziare a popolarlo con nuove battute.</p>\n" +
                "<h3>3.4 Creare una nuova battuta</h3>\n" +
                "<p>Entrati nella schermata interna al capitolo è possibile aggiungere nuove battute. L’aggiunta\n" +
                "di battute è simile all’utilizzo quotidiano di una qualsiasi applicazione di messaggistica:</p>\n" +
                "<ol>\n" +
                "<li>Cliccare sulla barra di inserimento testo posta nel margine inferiore dello schermo;</li>\n" +
                "<li>Scrivere il testo che andrà a comporre la battuta;</li>\n" +
                "<li>Cliccare sull’icona personaggio e selezionare un personaggio da associare alla battuta:\n" +
                "questo andrà a definire la voce che pronuncerà il testo contenuto nella battuta;</li>\n" +
                "<li>Cliccare sull’icona emoticon e selezionare un’emozione da associare alla battuta (opzionale):\n" +
                "l’emozione varia la voce del personaggio in modo da donare maggiore espressività\n" +
                "alla pronuncia del testo;</li>\n" +
                "<li>Confermare la creazione premendo l’apposito pulsante di \"invio\".</li></ol>\n" +
                "<p>A questo punto la battuta è disponibile nella schermata interna al capitolo.</p>\n" +
                "\n" +
                "<h3>3.5 Creare un nuovo personaggio</h3>\n" +
                "<p>Dalla schermata interna a uno sceneggiato o un capitolo accedere al menu a tendina posto\n" +
                "nell’angolo in alto a destra dello schermo:</p>\n" +
                "<ol>\n" +
                "<li>Premere sulla voce Crea personaggio;</li>\n" +
                "<li>Assegnare un nome al personaggio;</li>\n" +
                "<li>Assegnare una voce al personaggio;</li>\n" +
                "<li>Testare la voce premendo sul pulsante TEST VOCE (opzionale);</li>\n" +
                "<li>Caricare un avatar dalla memoria SD del dispositivo:\n" +
                "(a) Selezionare una fra le applicazioni offerte per la visualizzazione delle immagini;\n" +
                "(b) Navigare nella galleria per selezionare l’immagine desiderata: l’immagine verrà\n" +
                "rifiutata se la dimensione supera 1MB.</li>\n" +
                "<li>Confermare la creazione premendo il pulsante CREA PERSONAGGIO.</li></ol>\n" +
                "<p>Ad operazione conclusa verrà aperta la schermata contenente una lista di tutti i personaggi\n" +
                "creati all’interno dello sceneggiato.</p>\n" +
                "<h3>3.6 Eliminare uno sceneggiato</h3>\n" +
                "<p>Dalla home dell’applicazione effettuare una pressione prolungata sullo sceneggiato che si\n" +
                "vuole eliminare. Cliccare quindi sul pulsante di eliminazione posto nell’angolo in basso a\n" +
                "destra dello schermo.</p>\n" +
                "<h3>3.7 Eliminare un capitolo</h3>\n" +
                "<p>Dalla schermata interna allo sceneggiato effettuare una pressione prolungata sul capitolo che\n" +
                "si vuole eliminare. Cliccare quindi sul pulsante di eliminazione posto nell’angolo in basso a\n" +
                "destra dello schermo.</p>\n" +
                "<h3>3.8 Eliminare una battuta</h3>\n" +
                "<p>Dalla schermata interna al capitolo effettuare una pressione prolungata sulla battuta che\n" +
                "si vuole eliminare. Cliccare quindi sul pulsante di eliminazione posto nell’angolo in basso a\n" +
                "destra dello schermo.</p>\n" +
                "<h3>3.9 Modificare un capitolo</h3>\n" +
                "<p>Dalla schermata interna allo sceneggiato accedere al menu a tendina posto nell’angolo in\n" +
                "alto a destra dello schermo:</p><ol>\n" +
                "<li>Premere sulla voce Modifica capitolo;</li>\n" +
                "<li>Modificare i campi in base alle nuove preferenze.</li></ol>\n" +
                "<p>Alternativamente è possibile effettuare una pressione prolungata sul pulsante che identifica\n" +
                "il capitolo (dalla lista interna allo sceneggiato) e cliccare sul pulsante a forma di penna.</p>\n" +
                "<h3>3.10 Modificare una battuta</h3>\n" +
                "<p>Dalla schermata interna a un capitolo premere sulla battuta che si vuole modificare (pressione\n" +
                "normale, non prolungata). Cambiare quindi i vari campi in base alle nuove preferenze.</p>\n" +
                "<h3>3.11 Modificare un personaggio</h3>\n" +
                "<p>Dalla schermata interna a uno sceneggiato o un capitolo accedere al menu a tendina posto\n" +
                "nell’angolo in alto a destra dello schermo:</p><ol>\n" +
                "<li>Premere il pulsante Lista personaggi;</li>\n" +
                "<li>Premere sul personaggio desiderato;</li>\n" +
                "<li>Modificare i campi in base alle nuove preferenze.</li></ol>\n" +
                "<h3>3.12 Salvare lo sceneggiato</h3>\n" +
                "<p>Dalla schermata interna a uno sceneggiato accedere al menu a tendina posto nell’angolo in\n" +
                "alto a destra dello schermo:</p><ol>\n" +
                "<li>Premere il pulsante Salva progetto;</li>\n" +
                "<li>Visualizzazione del messaggio che conferma il salvataggio dello sceneggiato.</li></ol>\n" +
                "<p>Si segnala che l’applicazione implementa un sistema di salvataggio automatico interno alle\n" +
                "schermate di uno sceneggiato.</p>\n" +
                "<h3>3.13 Esportare lo sceneggiato</h3>\n" +
                "<p>Dalla schermata interna a uno sceneggiato accedere al menu a tendina posto nell’angolo in\n" +
                "alto a destra dello schermo:</p><ol>\n" +
                "<li>Selezione di una delle due opzioni di esportazione: audio (MP3) o video (MP4).</li>\n" +
                "<li>Avvio dell’esportazione dello sceneggiato.</li></ol>\n" +
                "<p>Il prodotto esportato può essere visionato attraverso:</p><ul>\n" +
                "<li>Un’applicazione per la riproduzione dell’audio nel caso di esportazione in formato\n" +
                "MP3;</li>\n" +
                "<li>Un’applicazione di tipo galleria per la visualizzazione delle immagini nel caso di esportazione\n" +
                "in formato MP4.</li></ul>\n" +
                "<h3>3.14 Condividere lo sceneggiato</h3>\n" +
                "<p>Dalla schermata interna a uno sceneggiato cliccare sull’icona di condivisione posta in alto a\n" +
                "destra dello schermo nella barra degli strumenti:</p><ol>\n" +
                "<li>Apertura dell’interfaccia attraverso cui è possibile selezionare l’applicazione su cui si\n" +
                "vuole condividere lo sceneggiato (in formato audio o video).</li>\n" +
                "<li>Selezionare l’applicazione su cui si desidera condividere lo sceneggiato.</li></ol>\n" +
                "\n" +
                "<h2>4 Guida alle funzionalità di SiVoDiM</h2>\n" +
                "<h3>4.1 Navigare fra le schermate principali</h3>\n" +
                "<p>Avviata l’applicazione è possibile accedere alla schermata per la creazione di una nuova voce\n" +
                "e alla schermata contenente la lista di tutti le voci disponibili. Nell’angolo in alto a sinistra è\n" +
                "presente il pulsante di accesso al drawer. Dal drawer è possibile accedere al manuale\n" +
                "utente o alle info dell’app.</p>\n" +
                "\n" +
                "<h3>4.2 Creare una nuova voce</h3>\n" +
                "<p>Dalla schermata Crea nuova voce è possibile aggiungere una nuove voce personalizzata\n" +
                "dall’utente:</p><ol>\n" +
                "<li>Inserire un nome per identificare univocamente la voce;</li>\n" +
                "<li>Selezionare il sesso della voce (Maschio, Femmina, Neutro, Sconosciuto);</li>\n" +
                "<li>Selezionare la lingua della voce (Italiano, Inglese, Tedesco, Francese);</li>\n" +
                "<li>Modificare i parametri designati negli slider per ottenere una voce personalizzata:<ul>\n" +
                "<li>Altezza: definisce la frequenza media della voce;</li>\n" +
                "<li>Profondità: altera il tono della voce per donarle più o meno corposità;</li>\n" +
                "<li>Energia: altera il tono della voce per donarle più o meno vivacità;</li>\n" +
                "<li>Accento: altera il tono della voce per donarle più o meno enfasi;</li>\n" +
                "<li>Velocità: definisce la velocità con cui la voce riproduce il testo.</ul></li>\n" +
                "<li>Premere il pulsante PLAY per testare il risultato ottenuto in tempo reale (opzionale);</li>\n" +
                "<li>Confermare la creazione della voce premendo il pulsante AGGIUNGI VOCE.</li></ol>\n" +
                "\n" +
                "<h3>4.3 Modificare una voce</h3>\n" +
                "<p>Dalla schermata Lista voci premere sulla voce desiderata per accedere all’interfaccia di\n" +
                "modifica:</p><ol>\n" +
                "<li>Modificare i campi come spiegato nella sezione Creare una nuova voce;</li>\n" +
                "</li>Confermare le modifiche apportate alla voce premendo il pulsante MODIFICA VOCE.</li></ol>\n" +
                "\n" +
                "<h2>5 Contatti e segnalazioni</h2>\n" +
                "<p>Nel caso si riscontrassero problemi durante il normale utilizzo dell’applicazione, si prega di\n" +
                "contattare il team di sviluppo inviando una segnalazione all’indirizzo email\n" +
                "starklabs.swe@gmail.com\n" +
                "Come oggetto dell’email si invita ad usare il tag [BUG REPORT SIVODIM]. In allegato al testo,\n" +
                "sono graditi screenshot che catturano la tipologia del problema e la schermata in cui si\n" +
                "è manifestato l’errore. Estremamente gradita è una descrizione precisa delle operazioni\n" +
                "compiute prima del verificarsi dell’errore, in modo da ricreare l’anomalia in fase di correzione.</p>\n" +
                "\n"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(this,HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
