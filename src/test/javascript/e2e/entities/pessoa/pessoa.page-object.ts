import { element, by, ElementFinder } from 'protractor';

export class PessoaComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-pessoa div table .btn-danger'));
    title = element.all(by.css('jhi-pessoa div h2#page-heading span')).first();

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

export class PessoaUpdatePage {
    pageTitle = element(by.id('jhi-pessoa-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nomeInput = element(by.id('field_nome'));
    emailInput = element(by.id('field_email'));
    passwordInput = element(by.id('field_password'));
    ativConcluidasInput = element(by.id('field_ativConcluidas'));
    pontEntregueInput = element(by.id('field_pontEntregue'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setNomeInput(nome) {
        await this.nomeInput.sendKeys(nome);
    }

    async getNomeInput() {
        return this.nomeInput.getAttribute('value');
    }

    async setEmailInput(email) {
        await this.emailInput.sendKeys(email);
    }

    async getEmailInput() {
        return this.emailInput.getAttribute('value');
    }

    async setPasswordInput(password) {
        await this.passwordInput.sendKeys(password);
    }

    async getPasswordInput() {
        return this.passwordInput.getAttribute('value');
    }

    async setAtivConcluidasInput(ativConcluidas) {
        await this.ativConcluidasInput.sendKeys(ativConcluidas);
    }

    async getAtivConcluidasInput() {
        return this.ativConcluidasInput.getAttribute('value');
    }

    async setPontEntregueInput(pontEntregue) {
        await this.pontEntregueInput.sendKeys(pontEntregue);
    }

    async getPontEntregueInput() {
        return this.pontEntregueInput.getAttribute('value');
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

export class PessoaDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-pessoa-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-pessoa'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
