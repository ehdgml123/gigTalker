package com.zerock.service;


import com.zerock.Entity.ProductionService;
import com.zerock.Entity.ProductionServiceImg;
import com.zerock.Entity.Users;
import com.zerock.Repository.ProductImgRepository;
import com.zerock.Repository.ProductionServiceRepository;
import com.zerock.dto.ProductServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductionServiceService {

    private final ProductionServiceRepository productionServiceRepository;
    private final ProductImgService productImgService;
    private final ProductImgRepository productImgRepository;

    public Long saveItem(ProductServiceDto productFormDto,
                         Users user, List<MultipartFile> productImgFileList) throws Exception {

        if (productImgFileList == null || productImgFileList.isEmpty()) {
            throw new IllegalArgumentException("이미지 파일이 반드시 포함되어야 합니다.");
        }

        ProductionService productionService = new ProductionService();
        productionService.setTitle(productFormDto.getTitle());
        productionService.setBasePrice(productFormDto.getBasePrice());
        productionService.setCategory(productFormDto.getCategory());
        productionService.setSubCategory(productFormDto.getSubCategory());
        productionService.setDescription(productFormDto.getDescription());
        productionService.setMaxWorkingDays(productFormDto.getMaxWorkingDays());
        productionService.setFreeRevisions(productFormDto.getFreeRevisions());
        productionService.setCommunicationMethod(productFormDto.getCommunicationMethod());
        productionService.setUser(user);

        productionServiceRepository.save(productionService);

        // 이미지 저장 처리
        saveProductImages(productionService, productImgFileList);

        return productionService.getId();
    }

    private void saveProductImages(ProductionService productionService, List<MultipartFile> productImgFileList) throws Exception {
        for (int i = 0; i < productImgFileList.size(); i++) {
            MultipartFile file = productImgFileList.get(i);

            ProductionServiceImg productionServiceImg = new ProductionServiceImg();
            productionServiceImg.setProductionService(productionService);
            productionServiceImg.setRepimgYn(i == 0 ? "Y" : "N");

            productImgService.saveProductImg(productionServiceImg, file);
            productImgRepository.save(productionServiceImg);
        }
    }

    public List<ProductionService> getProductsByCategory(String category) {

        return productionServiceRepository.findByCategory(category);
    }


    }

