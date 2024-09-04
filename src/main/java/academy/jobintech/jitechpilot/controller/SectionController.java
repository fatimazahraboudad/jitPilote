package academy.jobintech.jitechpilot.controller;

import academy.jobintech.jitechpilot.dto.SectionDTO;
import academy.jobintech.jitechpilot.dto.UserRequestDto;
import academy.jobintech.jitechpilot.dto.UserResponseDto;
import academy.jobintech.jitechpilot.serviceImpl.SectionServiceImpl;
import academy.jobintech.jitechpilot.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/section")
@CrossOrigin(
        origins = {"*"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT}
)

public class SectionController{
     @Autowired
     private SectionServiceImpl sectionService;
     @PostMapping("/board/{boardId}")
     private ResponseEntity<SectionDTO> createSectionInBoard(@PathVariable long boardId,@Valid @RequestBody SectionDTO sectionDTO) {
            SectionDTO sectioncreated = sectionService.createSection(boardId,sectionDTO);
            return new ResponseEntity<>(sectioncreated, HttpStatus.CREATED);
     }
     @GetMapping("/board/{boardId}")
     public List<SectionDTO> getSectionsByBoard(@PathVariable long boardId) {
         return sectionService.getSectionByBoards(boardId);
     }


     @GetMapping
     private ResponseEntity<List<SectionDTO>> getAllSection() {
         return ResponseEntity.ok(sectionService.getAllSection());
     }


     @GetMapping("{sectionId}")
     private ResponseEntity<SectionDTO> getSectionbyId(@PathVariable long sectionId) {
         return ResponseEntity.ok(sectionService.getSectionById(sectionId));
     }



     @PutMapping("{sectionId}")
     private ResponseEntity<SectionDTO> getSectionById(@PathVariable long sectionId,@Valid @RequestBody SectionDTO sectionDTO) {
         SectionDTO sectionDTO1=sectionService.updateSection(sectionId,sectionDTO);

         return new  ResponseEntity<>(sectionDTO1,HttpStatus.OK);
     }

     @DeleteMapping("{sectionId}")
     private ResponseEntity<Void> deleteSectionById(@PathVariable long sectionId) {
         sectionService.deleteSection(sectionId);
         return  ResponseEntity.noContent().build();
     }

     @PostMapping("{sectionId}/board/{boardId}")
     private ResponseEntity<String> assignSectionToBoard(@PathVariable long sectionId,@Valid @PathVariable long boardId) {
         sectionService.assignSectionToBoard(sectionId,boardId);
         return new ResponseEntity<>("Section assign to board successfully",HttpStatus.OK);
     }
 }
