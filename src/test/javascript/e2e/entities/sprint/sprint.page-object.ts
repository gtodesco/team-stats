import { element, by, ElementFinder } from 'protractor';

export class SprintComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-sprint div table .btn-danger'));
    title = element.all(by.css('jhi-sprint div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getText();
    }
}

export class SprintUpdatePage {
    pageTitle = element(by.id('jhi-sprint-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    numeroInput = element(by.id('field_numero'));
    pontPlanejadaInput = element(by.id('field_pontPlanejada'));
    pontConcluidasInput = element(by.id('field_pontConcluidas'));
    qtdDiasInput = element(by.id('field_qtdDias'));
    finalizadaInput = element(by.id('field_finalizada'));
    equipeSelect = element(by.id('field_equipe'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setNumeroInput(numero) {
        await this.numeroInput.sendKeys(numero);
    }

    async getNumeroInput() {
        return this.numeroInput.getAttribute('value');
    }

    async setPontPlanejadaInput(pontPlanejada) {
        await this.pontPlanejadaInput.sendKeys(pontPlanejada);
    }

    async getPontPlanejadaInput() {
        return this.pontPlanejadaInput.getAttribute('value');
    }

    async setPontConcluidasInput(pontConcluidas) {
        await this.pontConcluidasInput.sendKeys(pontConcluidas);
    }

    async getPontConcluidasInput() {
        return this.pontConcluidasInput.getAttribute('value');
    }

    async setQtdDiasInput(qtdDias) {
        await this.qtdDiasInput.sendKeys(qtdDias);
    }

    async getQtdDiasInput() {
        return this.qtdDiasInput.getAttribute('value');
    }

    getFinalizadaInput() {
        return this.finalizadaInput;
    }

    async equipeSelectLastOption() {
        await this.equipeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async equipeSelectOption(option) {
        await this.equipeSelect.sendKeys(option);
    }

    getEquipeSelect(): ElementFinder {
        return this.equipeSelect;
    }

    async getEquipeSelectedOption() {
        return this.equipeSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class SprintDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-sprint-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-sprint'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
