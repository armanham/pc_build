package com.bdg.pc_build.builder.repository;

import com.bdg.pc_build.builder.model.dto.ComputerDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComputerDAO {

    private static List<ComputerDTO> computerDTOList = new ArrayList<>();

    public List<ComputerDTO> getComputerDTOList() {
        return computerDTOList;
    }

    public void setComputerDTOList(List<ComputerDTO> computerDTOList) {
        ComputerDAO.computerDTOList = computerDTOList;
    }

    public void addComputerDTO(ComputerDTO computerDTO) {
        computerDTOList.add(computerDTO);
    }

    public void removeComputerDTO(ComputerDTO computerDTO) {
        computerDTOList.remove(computerDTO);
    }
}