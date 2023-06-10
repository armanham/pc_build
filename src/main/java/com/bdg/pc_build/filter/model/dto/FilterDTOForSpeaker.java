package com.bdg.pc_build.filter.model.dto;

import com.bdg.pc_build.filter.model.request.peripheral.FilterRequestForSpeaker;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class FilterDTOForSpeaker {

    Double minPrice;
    Double maxPrice;
    Integer frequency;
    String powerSource;
    Double cableLength;
    String dimension;

    public FilterDTOForSpeaker(final FilterRequestForSpeaker request){
        this.powerSource = request.powerSource();
        this.dimension = request.dimension();
        if(request.minPrice() != null && !request.minPrice().isBlank()){
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if(request.maxPrice() != null && !request.maxPrice().isBlank()){
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if(request.frequency() != null && !request.frequency().isBlank()){
            this.frequency = Integer.valueOf(request.frequency());
        }if(request.cableLength() != null && !request.cableLength().isBlank()){
            this.cableLength = Double.valueOf(request.cableLength());
        }
    }
}
