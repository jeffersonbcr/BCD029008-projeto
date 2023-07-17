package engtelecom.bcd.jpa.worldcup;

import engtelecom.bcd.jpa.worldcup.repositores.*;
import engtelecom.bcd.jpa.worldcup.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@SpringBootApplication
public class WorldCupApplication {
    private final EventRepository eventRepository;
    private final ClassificationHasStageRepository classificationHasStageRepository;

    public WorldCupApplication(EventRepository eventRepository,
                               ClassificationHasStageRepository classificationHasStageRepository) {
        this.eventRepository = eventRepository;
        this.classificationHasStageRepository = classificationHasStageRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(WorldCupApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(MemberRepository memberRepository,
                                    CoachRepository coachRepository,
                                    TeamRepository teamRepository,
                                    SeasonRepository seasonRepository,
                                    StageRepository stageRepository,
                                    PositionRepository positionRepository,
                                    MatchRepository matchRepository,
                                    StadiumRepository stadiumRepository,
                                    ClassificationRepository classificationRepository,
                                    MemberHasMatchHasEventRepository memberHasMatchHasEventRepository,
                                    MatchHasMembersRepository matchHasMembersRepository,
                                    MatchHasSeasonRepository matchHasSeasonRepository,
                                    ClassificationHasStageRepository classificationHasStageRepository)
    {
        return (args) -> {
            System.out.println("START APP");
            Scanner scanner = new Scanner(System.in);

            boolean stateApp = true;
            while (stateApp)
            {
                System.out.println("OPEN");


                String opcao = scanner.nextLine();

                switch (opcao.toLowerCase())
                {
                    case "1":
                        System.out.println("Opção de Inserção Selecionada");
                        insertMenu(memberRepository,
                                coachRepository,
                                teamRepository,
                                seasonRepository,
                                stageRepository,
                                positionRepository,
                                matchRepository,
                                stadiumRepository,
                                classificationRepository,
                                memberHasMatchHasEventRepository,
                                matchHasMembersRepository,
                                matchHasSeasonRepository,
                                classificationHasStageRepository);
                        break;
                    case "2":
                        System.out.println("SELECT");
                        selectMenu(memberRepository,
                                coachRepository,
                                teamRepository,
                                seasonRepository,
                                stageRepository,
                                positionRepository,
                                matchRepository,
                                stadiumRepository,
                                classificationRepository,
                                memberHasMatchHasEventRepository,
                                matchHasMembersRepository,
                                matchHasSeasonRepository,
                                classificationHasStageRepository);
                        break;
                    case "3":
                        System.out.println("UPDATE");
                        break;
                    case "4":
                        System.out.println("DELETE");

                        break;
                    default:
                        stateApp = false;
                        System.out.println("CLOSE");
                        break;

                }
            }
        };
    }

    public void insertMenu(MemberRepository memberRepository,
                           CoachRepository coachRepository,
                           TeamRepository TeamRepository,
                           SeasonRepository seasonRepository,
                           StageRepository stageRepository,
                           PositionRepository positionRepository,
                           MatchRepository matchRepository,
                           StadiumRepository stadiumRepository,
                           ClassificationRepository classificationRepository,
                           MemberHasMatchHasEventRepository memberHasMatchHasEventRepository,
                           MatchHasMembersRepository matchHasMembersRepository,
                           MatchHasSeasonRepository matchHasSeasonRepository,
                           ClassificationHasStageRepository classificationHasStageRepository)
    {
        boolean stateApp = true;
        Scanner scanner = new Scanner(System.in);
        while (stateApp)
        {
            System.out.println("OPÇÕES:");
            System.out.println("""
				1 - INSERT EVENTO\n
				2 - INSERT PARTIDA\n
				3 - INSERT Jogador\n
				4 - INSERT Tecnico\n
				5 - INSERT Time\n
				6 - INSERT Edição\n
				7 - INSERT Estadio\n
				8 - INSERT Classificação a um Time\n
				9 - INSERT Jogador a uma partida\n
				10 - INSERT Jogador a um Time\n
				11 - INSERT Tecnico a uma seleção\n
				12 - INSERT posição de Futebol\n
				13 - INSERT Classificação \n
				14 - INSERT tipo de Evento\n
				15 - INSERT Fase\n
				16 - INSERT Edição a uma Partida""");
            String opcao = scanner.nextLine();
            switch (opcao)
            {
                case "1":
                    System.out.println("INSERT EVENTO: \n");
                    System.out.print("Digite o id da partida: ");
                    String matchInput = scanner.nextLine();

                    Optional<Match> partida = matchRepository.findById(Integer.parseInt(matchInput));
                    if(partida.isEmpty()){
                        System.out.println("id não encontrada");
                        break;
                    }

                    System.out.print("INPUT ID do Jogador relacionado com o Evento: ");
                    String jogadorStr = scanner.nextLine();

                    Optional<Member> jogador = memberRepository.findById(Integer.parseInt(jogadorStr));
                    if (jogador.isEmpty()){
                        System.out.println("Jogador não encontrado");
                        break;
                    }

                    Optional<MatchHasMembers> jogadoresDaPartida = matchHasMembersRepository.findByMemberAndMatch(jogador.get(), partida.get());
                    if(jogadoresDaPartida.isEmpty()){
                        System.out.println("Este jogador não participou da partida");
                        break;
                    }

                    System.out.print("Digite o numero identificador do Evento ocorrido: ");
                    String eventoStr = scanner.nextLine();

                    Optional<Event> evento = eventRepository.findById(Integer.parseInt(eventoStr));
                    if (evento.isEmpty()){
                        System.out.println("EVENTO não existe");
                        break;
                    }

                    System.out.print("INPUT o minuto em que ocorreu o Evento: ");
                    String tempoDeJogo = scanner.nextLine();

                    MemberHasMatchHasEvent eventoDePartida = new MemberHasMatchHasEvent(jogadoresDaPartida.get(), evento.get(), Double.parseDouble(tempoDeJogo));
                    memberHasMatchHasEventRepository.save(eventoDePartida);

                    break;
                case "2":
                    System.out.println("INSERT de uma partida selecionado. Informe os dados da partida: \n");
                    System.out.print("Qual é data que ocorreu/vai ocorrer a partida: ");
                    String _dateStr = scanner.nextLine();
                    System.out.println("INPUT DO ID do estadio em vai ocorrer a partida: ");
                    String estadioID = scanner.nextLine();
                    Optional<Stadium> estadio = stadiumRepository.findById(Integer.parseInt(estadioID));
                    if(estadio.isEmpty()) {
                        System.out.println("Estadio não encontrado");
                        break;
                    }
                    System.out.print("INPUT id da primeira Seleção a jogar na Partida: ");
                    String time_1 = scanner.nextLine();
                    Optional<Team> selecaoCasa = TeamRepository.findById(Integer.parseInt(time_1));
                    if(selecaoCasa.isEmpty()){
                        System.out.println("Selecao não encontrada");
                        break;
                    }
                    System.out.print("INPUT id da segunda Seleção a jogar na Partida: ");
                    String time_2 = scanner.nextLine();
                    Optional<Team> selecaoConvidada = TeamRepository.findById(Integer.parseInt(time_2));
                    if(selecaoConvidada.isEmpty()){
                        System.out.println("Selecao não encontrada");
                        break;
                    }
                    if(!Objects.equals(selecaoCasa.get().getSeason(), selecaoConvidada.get().getSeason()))
                    {
                        System.out.println("As Seleções não são da mesma edição.");
                        break;
                    }
                    Date date_ = Date.valueOf(_dateStr);
                    Match novaPartida = new Match(date_, estadio.get(), selecaoCasa.get(), selecaoConvidada.get());
                    matchRepository.save(novaPartida);

                    break;
                case "3":
                    System.out.println("INSERT de um jogador selecionado: \n");
                    System.out.print("INPUT o Nome do Jogador: ");
                    String nameJogador = scanner.nextLine();
                    System.out.print("INPUT o Sobrenome do Jogador: ");
                    String lastNameJogador = scanner.nextLine();
                    System.out.print("INPUT da data em que ele nasceu  (Formato: **Ano-Mes-Dia** ): ");
                    String dateNascJogador = scanner.nextLine();
                    Date dateNascJogador_ = Date.valueOf(dateNascJogador);
                    System.out.print("INPUT da Nacionalidade do Jogador: ");
                    String nacionalidadeJogador = scanner.nextLine();
                    Person person_m = new Person(nameJogador, lastNameJogador, dateNascJogador_);
                    Nation nation_m = new Nation(nacionalidadeJogador);
                    Member novoJogador = new Member(person_m, nation_m);
                    memberRepository.save(novoJogador);

                    break;
                case "4":
                    System.out.println("INSERT de um tecnico selecionado. Informe os dados do Tecnico: \n");
                    System.out.print("INPUT o Nome do Tecnico: ");
                    String nameTecnico = scanner.nextLine();
                    System.out.print("INPUT o Sobrenome do Tecnico: ");
                    String lastNameTecnico = scanner.nextLine();
                    System.out.print("INPUT a data em que ele nasceu: ");
                    String dateNascTecnico = scanner.nextLine();
                    Date dateNascTecnico_ = Date.valueOf(dateNascTecnico);
                    System.out.print("INPUT a Nacionalidade do Tecnico: ");
                    String nacionalidadeTecnico = scanner.nextLine();
                    Person person_t = new Person(nameTecnico, lastNameTecnico, dateNascTecnico_);
                    Nation nation_t = new Nation(nacionalidadeTecnico);
                    Coach novoTecnico = new Coach(person_t, nation_t);
                    coachRepository.save(novoTecnico);

                    break;
                case "5":
                    System.out.println("INSERT uma Seleção selecionado. Informe os dados da Seleção: \n");
                    System.out.print("INPUT o Pais da Seleção: ");
                    String paisSelecao = scanner.nextLine();
                    System.out.print("INPUT ID da Edição da Copa que este país vai participar: ");
                    String EdicaoID = scanner.nextLine();
                    Optional<Season> edicao_ = seasonRepository.findById(Integer.parseInt(EdicaoID));
                    if(edicao_.isEmpty()){
                        System.out.println("Edição de Copa não encontrada");
                        break;
                    }
                    Nation nation_n = new Nation(paisSelecao);
                    Team novaSelecao = new Team(nation_n, edicao_);
                    TeamRepository.save(novaSelecao);
                    System.out.println("Selecao salva com sucesso.");
                    break;
                case "6":
                    System.out.println("INSERT de  uma Edição selecionado. Informe os dados da edição: \n");
                    System.out.print("INPUT do Ano em que da Edição de Copa : ");
                    String anoEdicao = scanner.nextLine();
                    System.out.print("Informe o país em que a Edição de Copa irá ser prestigiada: ");
                    String paisEdicao = scanner.nextLine();
                    Nation nation_e = new Nation(paisEdicao);
                    Season edicao = new Season(nation_e,Integer.parseInt(anoEdicao));
                    seasonRepository.save(edicao);

                    break;
                case "7":
                    System.out.println("INSERT de um Estadio selecionado: \n");
                    System.out.print("INPUT o Nome do Estadio: ");
                    String nomeEstadio = scanner.nextLine();
                    Stadium estadio__ = new Stadium(nomeEstadio);
                    stadiumRepository.save(estadio__);

                    break;
                case "8":
                    System.out.println("Adicionando uma Classifição a uma Seleção: \n");
                    System.out.print("Informe o numero Identificador da Seleção a ser classificada: ");
                    String idSelecaoClassf = scanner.nextLine();
                    Optional<Team> sClassf = TeamRepository.findById(Integer.parseInt(idSelecaoClassf));
                    if(sClassf.isEmpty()) {
                        System.out.println("Não foi encontrado esta seleção");
                        break;
                    }
                    System.out.print("INPUT ID da posição a ser atribuida a seleção: ");
                    String  idClassf = scanner.nextLine();
                    Optional<Classification> classf = classificationRepository.findById(Integer.parseInt(idClassf));
                    if (classf.isEmpty())
                    {
                        System.out.println("Não foi encontrado esta classifcaoção");
                        break;
                    }

                    System.out.print("INPUT ID da fase em que está ocorrendo a Classificação: ");
                    String idFase_ = scanner.nextLine();
                    Optional<Stage> classFase = stageRepository.findById(Integer.parseInt(idFase_));
                    if(classFase.isEmpty()){
                        System.out.println("Fase não encontrada");
                        break;
                    }

                    ClassificationHasStageRepository classificationHasStageRepository = new ClassificationHasStage(classf.get(), sClassf.get(), classFase.get());
                    classificationHasStageRepository.save(classificationHasStageRepository);

                    break;
                case "9":
                    System.out.println("Adicionando um Jogador em uma partida selecionado. Informe qual é o Jogador com suas informações: \n");
                    System.out.print("Informe o numero indentificador do Jogador desejado: ");
                    String jogadorInformado = scanner.nextLine();

                    Optional <Member> findJogador = memberRepository.findById(Integer.parseInt(jogadorInformado));
                    if(findJogador.isEmpty())
                    {
                        System.out.println("ID JOGADOR ERRADO");
                        break;
                    }

                    System.out.print("INPUT ID DA POSIÇÃO DO JOGADOR: ");
                    String posicaoPartida = scanner.nextLine();

                    Optional<Position>  findPosicao = positionRepository.findById(Integer.parseInt(posicaoPartida));
                    if(findPosicao.isEmpty()){
                        System.out.println("POSIÇÃO NÃO ENCONTRADA");
                        break;
                    }

                    System.out.print("INPUT ID DA PARTIDA E DO JOGADOR EM ANDAMENTO: ");
                    String partidaInfromada = scanner.nextLine();

                    Optional <Match>  findPartida = matchRepository.findById(Integer.parseInt(partidaInfromada));
                    if(findPartida.isEmpty()){
                        System.out.println("PARTIDA NÃO ENCONTRADA");
                        break;
                    }

                    MatchHasMembers membersHasmatch_n = new MatchHasMembers(findJogador.get(), findPartida.get(), findPosicao.get());
                    matchHasMembersRepository.save(membersHasmatch_n);

                    break;
                case "10":
                    System.out.println("INPUT JOGADOR INFO: \n");
                    System.out.print("IPUT ID JOGADOR: ");
                    String memberSelected = scanner.nextLine();

                    Optional<Member> findJogador_ = memberRepository.findById(Integer.parseInt(memberSelected));
                    if(findJogador_.isEmpty()){
                        System.out.println("ID INCORRETO");
                        break;
                    }

                    System.out.print("INPUT ID JOGADOR: ");
                    String jogador_i = scanner.nextLine();

                    Optional<Team> findSelecao_ = TeamRepository.findById(Integer.parseInt(jogador_i));
                    if(findSelecao_.isEmpty()) {
                        System.out.println("Time não encontrado");
                        break;
                    }


                    findSelecao_.get().addMember(findJogador_.get());
                    TeamRepository.save(findSelecao_.get());
                    break;
                case "11":
                    System.out.println("INSERT de um Jogador em uma seleção selecionado: \n");
                    System.out.print("IPUT ID do TECNICO desejado: ");
                    String coachTeam = scanner.nextLine();

                    Optional<Coach> findTecnico_ = coachRepository.findById(Integer.parseInt(coachTeam));
                    if(findTecnico_.isEmpty()){
                        System.out.println("Tecnico não Encontrado");
                    }

                    System.out.print("IPUT NOME da Seleção do Tecnico: ");
                    String selcTecnico = scanner.nextLine();

                    Optional<Team> teamFind = TeamRepository.findById(Integer.parseInt(selcTecnico));
                    if(teamFind.isEmpty()) {
                        System.out.println("Selecao não encontrada");
                        break;
                    }

                    teamFind.get().addCoach(findTecnico_.get());
                    TeamRepository.save(teamFind.get());

                    System.out.println("INSERT de um Tecnico a uma seleção com sucesso");
                    break;
                case "12":
                    System.out.println("Adicionando uma posição de Futebol. Informe quais são suas informações: \n");
                    System.out.print("INPUT do NOME da posição: ");
                    String tituloPos = scanner.nextLine();

                    Position posicao = new Position(tituloPos);
                    positionRepository.save(posicao);
                    break;
                case "13":
                    System.out.println("INSERT de uma Classificação: \n");
                    System.out.print("INPUT ID da classificação: ");
                    String classSel = scanner.nextLine();

                    Classification classification_n = new Classification((int) Double.parseDouble(classSel));
                    classificationRepository.save(classification_n);

                    break;
                case "14":
                    System.out.println("INSERT de um Evento de Futebol (Gol, Cartao Amarelo, Cartao Vermelho, não gol): \n");
                    System.out.print("INPUT NOME do Evento: ");
                    String tituloEvent = scanner.nextLine();

                    Event event_n = new Event(tituloEvent);
                    eventRepository.save(event_n);

                    break;
                case "15":
                    System.out.println("INSERT uma Fase da Copa Mundial: \n");
                    System.out.print("IPUT NOME da Fase: ");
                    String stage_n = scanner.nextLine();

                    Stage fase = new Stage(stage_n);
                    stageRepository.save(fase);
                    System.out.println("Sucesso");
                    break;
                case "16":
                    System.out.println("INSERT de uma edição a uma Partida: \n");
                    System.out.print("IPUT ID da Partida: ");
                    String matchInput_ = scanner.nextLine();
                    Optional<Match> optionalPartida = matchRepository.findById(Integer.parseInt(matchInput_));
                    if (optionalPartida.isEmpty()){
                        System.out.println("Partida não encontrada");
                        break;
                    }
                    System.out.print("IPUT ID da Edição: ");
                    String edicaoStr_ = scanner.nextLine();
                    Optional<Season> edicaoOptional = seasonRepository.findById(Integer.parseInt(edicaoStr_));
                    if(edicaoOptional.isEmpty()){
                        System.out.println("Edição não encontrada");
                        break;
                    }
                    System.out.print("INPUT ID da Fase que pertence esta partida: ");
                    String faseStr_ = scanner.nextLine();
                    Optional<Stage> faseOptional = stageRepository.findById(Integer.parseInt(faseStr_));
                    if (faseOptional.isEmpty()){
                        System.out.println("Fase não existe no sistema");
                        break;
                    }

                    MatchHasSeason partidasDaEdicao = new MatchHasSeason();
                    matchHasSeasonRepository.save(partidasDaEdicao);
                    break;
                default:
                    stateApp = false;
                    System.out.println("EXIT APP");
                    break;
            }
        }
    }

    public void selectMenu(MemberRepository memberRepository,
                           CoachRepository coachRepository,
                           TeamRepository TeamRepository,
                           SeasonRepository seasonRepository,
                           StageRepository stageRepository,
                           PositionRepository positionRepository,
                           MatchRepository matchRepository,
                           StadiumRepository stadiumRepository,
                           ClassificationRepository classificationRepository,
                           MemberHasMatchHasEventRepository memberHasMatchHasEventRepository,
                           MatchHasMembersRepository matchHasMembersRepository,
                           MatchHasSeasonRepository matchHasSeasonRepository,
                           ClassificationHasStageRepository classificationHasStageRepository)
    {
        boolean stateApp = true;
        Scanner scanner = new Scanner(System.in);
        while (stateApp)
        {
            System.out.println("MENU:");
            System.out.println("""
					1 - Quais os jogadores e técnicos de cada seleção\n
					2 - Quais jogadores participaram de uma determinada partida\n
					3 - Informações sobre um Determinado Jogador (Data de Nascimento, Nacionalidade)\n
					4 - Qual a posição de um Jogador em uma determinada partida\n
					5 - Listar todos os atacantes de todos os times, da edição x\n
					6 - Quantas copas possui cada seleção\n
					7 - Qual seleção participou de mais copas""");
            String opcao = scanner.nextLine();
            switch (opcao)
            {
                case "1":
                    System.out.println("SELECT ALL para Jogadores e Técnicos de cada Seleção");

                    Iterable<Team> allTeam = TeamRepository.findAll();

                    StringBuilder sb1 = new StringBuilder();

                    sb1.append(String.format("\n", "Seleção", "Edição" ,"Nome", "Sobrenome"));
                    allTeam.forEach(team -> {
                        Set<Member> sbPlayers = team.getMember_n();
                        sbPlayers.forEach(member -> {
                            sb1.append(String.format(team.getIdNation().getCountry(), team.getSeason().getYear()));
                            sb1.append(String.format("\n", member.getIdPerson().getName(), member.getIdPerson().getName()));
                        });
                        Set<Coach> sbTecnico = team.getCoach_n();
                        sbTecnico.forEach(tecnico -> {
                            sb1.append(String.format(team.getIdNation().getCountry(), team.getSeason().getYear()));
                            sb1.append(String.format("\n", tecnico.getIdPerson().getName(), tecnico.getIdPerson().getName()));
                        });
                    });
                    System.out.println(sb1);
                    break;
                case "2":
                    System.out.println("SELECT ALL para jogadores que participaram de uma certa partida: ");
                    System.out.print("Digite id da partida: ");
                    String procurarPartida = scanner.nextLine();

                    Optional<Match> partida_ = matchRepository.findById(Integer.parseInt(procurarPartida));
                    if(partida_.isEmpty()){

                        break;
                    }

                    Optional<Iterable<MatchHasMembers>> jogadoresDaPartida = matchHasMembersRepository.findByPartida(partida_.get());
                    if (jogadoresDaPartida.isEmpty()){

                        break;
                    }

                    jogadoresDaPartida.get().forEach(jogadoresDaPartida1 -> {

                    });
                    break;
                case  "3":
                    System.out.println("SELECT de um determindado Jogador: ");
                    System.out.print("INPUT ID do Jogador: ");
                    String buscaJogador = scanner.nextLine();

                    Optional<Member> jogadorOptional_ = memberRepository.findById(Integer.parseInt(buscaJogador));
                    if (jogadorOptional_.isEmpty()){
                        System.out.println("Jogador nao encontrado");
                        break;
                    }

                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(String.format("\n", "Nome", "Sobrenome" ,"DataNasc", "Nacionalidae"));

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    sb3.append(String.format("\n", jogadorOptional_.get().getIdPerson().getName(), jogadorOptional_.get().getIdPerson().getLastName(), dateFormat.format(jogadorOptional_.get().getIdPerson().getBirthDate()), jogadorOptional_.get().getIdNation().getCountry()));
                    System.out.println(sb3);
                    break;
                case "4":
                    System.out.println("Selecionado opção para listar a posição de um jogador em uma determinada partida: ");
                    System.out.println("INPUT ID do Jogador a ser buscado");
                    String strJogador = scanner.nextLine();
                    Optional<Member> jogadorPosicao = memberRepository.findById(Integer.parseInt(strJogador));
                    if (jogadorPosicao.isEmpty()){
                        System.out.println("Jogador não encontrado");
                        break;
                    }
                    System.out.print("INPUT ID da Partida a ser buscada: ");
                    String  strPartida = scanner.nextLine();
                    Optional<Match> optionalPartida = matchRepository.findById(Integer.parseInt(strPartida));
                    if (optionalPartida.isEmpty()){
                        System.out.println("Partida não encontrado");
                        break;
                    }

                    Optional<MatchHasMembers> optionalJogadoresDaPartida = matchHasMembersRepository.findByMemberAndMatch(jogadorPosicao.get(), optionalPartida.get());
                    if (optionalJogadoresDaPartida.isEmpty()){
                        System.out.println("Jogador não encontrado");
                        break;
                    }

                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(String.format("\n", "Nome", "Sobrenome" ,"Posição"));
                    sb4.append(String.format("\n", jogadorPosicao.get().getIdPerson().getName(), jogadorPosicao.get().getIdPerson().getLastName(), optionalJogadoresDaPartida.get().getPosition().getName()));
                    System.out.println(sb4);
                    break;
                case "5":
                    System.out.println("SELECT ALL dos atacantes de todos os times, da edição x");
                    System.out.print("INPUT do ano da Copa que deseja buscar: ");
                    String edicaoStr = scanner.nextLine();
                    Optional<Season> optionalEdicao = seasonRepository.findByYear(Integer.parseInt(edicaoStr));
                    if(optionalEdicao.isEmpty()){
                        System.out.println("COPA SEM DATA");
                        break;
                    }

                    Optional<Iterable<MatchHasSeason>> optionalIterable = matchHasSeasonRepository.findByEdicao(optionalEdicao.get());
                    if(optionalIterable.isEmpty()){
                        System.out.println("Edição sem DATA");
                        break;
                    }

                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(String.format("Nome", "Sobrenome", "Nacionalidade"));
                    sb5.append("\n");

                    optionalIterable.get().forEach(partidasDaEdicao -> {
                        Optional<Iterable<MatchHasMembers>> optionalJogadoresDaPartidaIterable = matchHasMembersRepository.findByPartida(partidasDaEdicao.getMatch());
                        optionalJogadoresDaPartidaIterable.ifPresent(jogadoresDaPartidas -> jogadoresDaPartidas.forEach(jogadoresDaPartida1 -> {
                            if(jogadoresDaPartida1.getPosition().getName().equals("Atacante")) {
                                sb5.append(String.format(jogadoresDaPartida1.getMember().getIdPerson().getName(), jogadoresDaPartida1.getMember().getIdPerson().getLastName(), jogadoresDaPartida1.getMember().getIdNation().getCountry()));
                            }
                        }));
                    });
                    System.out.println(sb5);
                    break;
                case "6":
                    System.out.println("SELECT Quantas copas possui cada seleção: ");
                    Iterable<Team> allSelecoes = TeamRepository.findAll();
                    HashMap<String, Integer> countSelecoes = new HashMap<>();

                    allSelecoes.forEach(selecao -> {
                        if(! countSelecoes.containsKey(selecao.getIdNation().getCountry())) {
                            countSelecoes.put(selecao.getIdNation().getCountry(), 1);
                        } else {
                            countSelecoes.put(selecao.getIdNation().getCountry(), countSelecoes.get(selecao.getIdNation().getCountry()) + 1);
                        }
                    });

                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(String.format("Seleção", "Quantida de Edições"));
                    sb6.append("\n");

                    for (String pais : countSelecoes.keySet())
                    {
                        sb6.append(String.format("\n", pais, countSelecoes.get(pais)));
                    }
                    System.out.println(sb6);
                    break;
                case "7":
                    System.out.println("SELECT da seleção participou de mais copas: ");
                    Iterable<Team> allSelecoes_ = TeamRepository.findAll();
                    HashMap<String, Integer> countSelecoes_ = new HashMap<>();

                    allSelecoes_.forEach(selecao -> {
                        if(! countSelecoes_.containsKey(selecao.getIdNation().getCountry())) {
                            countSelecoes_.put(selecao.getIdNation().getCountry(), 1);
                        } else {
                            countSelecoes_.put(selecao.getIdNation().getCountry(), countSelecoes_.get(selecao.getIdNation().getCountry()) + 1);
                        }
                    });

                    StringBuilder sb7 = new StringBuilder();
                    int maxValueInMap = (Collections.max(countSelecoes_.values()));
                    for (Map.Entry<String, Integer> entry :
                            countSelecoes_.entrySet()) {

                        if (entry.getValue() == maxValueInMap) {
                            sb7.append(String.format(entry.getKey(), entry.getValue()));
                        }
                    }
                    System.out.println(sb7);
                    break;
                default:
                    System.out.println("EXIT");
                    stateApp = false;
                    break;
            }
        }
    }


}
