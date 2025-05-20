package services;

import Repository.PacientetRepository;
import model.Pacientet.Pacientet;
import model.Pacientet.PacientetDTO.CreatePacientetDTO;

public class PacientetService {
    private PacientetRepository pacientetRepository;
    public PacientetService(){
        this.pacientetRepository = new PacientetRepository();
    }
    public Pacientet getById(int id) throws Exception{
        if(id <= 0){
            throw new Exception("Id does not exist!");
        }
        Pacientet pacient = this.pacientetRepository.getById(id);
        if(pacient == null){
            throw new Exception("Pacientet with Id: " + id + " does not exist!");
        }
        return pacient;
    }

    public Pacientet create(CreatePacientetDTO createPacientet) throws Exception{
        String gjinia = createPacientet.getGjinia().trim();
        if (!gjinia.equalsIgnoreCase("Mashkull") && !gjinia.equalsIgnoreCase("Femër")) {
            throw new Exception("Information is not valid! ...");
        }

        Pacientet pacient = this.pacientetRepository.create(createPacientet);
        if(pacient == null){
            throw new Exception("Pacientet is not created!");
        }
        return pacient;
    }
}
