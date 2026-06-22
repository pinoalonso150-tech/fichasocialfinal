package cl.duoc.fichasocial.controller;

import cl.duoc.fichasocial.dto.FichaSocialDTO;
import cl.duoc.fichasocial.service.FichaSocialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fichasocial")
public class FichaSocialController {

    private final FichaSocialService fichaSocialService;

    public FichaSocialController(FichaSocialService fichaSocialService) {
        this.fichaSocialService = fichaSocialService;
    }

    @GetMapping("/{rut}")
    public ResponseEntity<FichaSocialDTO> obtenerFichaSocial(@PathVariable String rut) {
        FichaSocialDTO fichaSocial = fichaSocialService.obtenerFichaCompleta(rut);
        if (fichaSocial == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fichaSocial);
    }
}
