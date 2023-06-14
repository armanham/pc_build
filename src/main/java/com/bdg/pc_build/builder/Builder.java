package com.bdg.pc_build.builder;

import com.bdg.pc_build.product.model.entity.computer.Computer;

public class Builder {

    private Computer computer;

    public Builder(final Computer computer) {
        compatibilityCpuSocketWithMotherboardSocket();
        this.computer = computer;
    }


    //CPU - Motherboard compatibility via Socket
    private void compatibilityCpuSocketWithMotherboardSocket(){
        if (computer.getCpu() == null || computer.getMotherboard() == null){
            return;
        }
        if (!computer.getMotherboard().getSocketType().equals(computer.getCpu().getSocketType())){
            //todo notCompEx
            throw new IllegalArgumentException();

        }

    }
}