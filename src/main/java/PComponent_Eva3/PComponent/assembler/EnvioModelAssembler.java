package PComponent_Eva3.PComponent.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.EnvioControllerV2;
import PComponent_Eva3.PComponent.model.Envio;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {
    @SuppressWarnings("null")
    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioControllerV2.class).getEnvioById(Integer.valueOf(envio.getId()))).withSelfRel(),
                linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withRel("envio"),
                linkTo(methodOn(EnvioControllerV2.class).updateEnvio(Integer.valueOf(envio.getId()), envio)).withRel("actualizar"),
                linkTo(methodOn(EnvioControllerV2.class).deleteEnvio(Integer.valueOf(envio.getId()))).withRel("eliminar"),
                linkTo(methodOn(EnvioControllerV2.class).updatePartialEnvio(Integer.valueOf(envio.getId()), envio)).withRel("actualizar-parcial")
        );
    }
}