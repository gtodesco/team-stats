import { element, by, ElementFinder } from 'protractor';

export class EquipeComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-equipe div table .btn-danger'));
    title = element.all(by.css('jhi-equipe div h2#page-heading span')).first();

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

export class EquipeUpdatePage {
    pageTitle = element(by.id('jhi-equipe-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nomeInput = element(by.id('field_nome'));
    emailScrumMasterInput = element(by.id('field_emailScrumMaster'));
    pessoasSelect = element(by.id('field_pessoas'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setNomeInput(nome) {
        await this.nomeInput.sendKeys(nome);
    }

    async getNomeInput() {
        return this.nomeInput.getAttribute('value');
    }

    async setEmailScrumMasterInput(emailScrumMaster) {
        await this.emailScrumMasterInput.sendKeys(emailScrumMaster);
    }

    async getEmailScrumMasterInput() {
        return this.emailScrumMasterInput.getAttribute('value');
    }

    async pessoasSelectLastOption() {
        await this.pessoasSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async pessoasSelectOption(option) {
        await this.pessoasSelect.sendKeys(option);
    }

    getPessoasSelect(): ElementFinder {
        return this.pessoasSelect;
    }

    async getPessoasSelectedOption() {
        return this.pessoasSelect.element(by.css('option:checked')).getText();
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

export class EquipeDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-equipe-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-equipe'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
