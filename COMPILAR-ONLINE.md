# Como compilar o WitcherSigns online (sem escrever código)

Este projeto inclui um workflow do GitHub Actions para tentar compilar o mod automaticamente com Java 8 e Gradle 4.10.3.

## Passos
1. Entre em https://github.com/ e crie uma conta ou entre na sua conta.
2. Crie um repositório novo chamado `WitcherSigns` (pode ser privado ou público).
3. Na página do repositório, escolha **Add file → Upload files**.
4. Envie o conteúdo inteiro deste ZIP, incluindo a pasta oculta `.github` e o arquivo `.github/workflows/build.yml`. Se o navegador/Windows ocultar `.github`, crie essa pasta no repositório usando **Add file → Create new file** e digite `.github/workflows/build.yml`; copie o conteúdo do arquivo local.
5. Clique em **Commit changes**.
6. Abra a aba **Actions** do repositório. O workflow **Build WitcherSigns (Minecraft Forge 1.7.10)** deverá iniciar automaticamente.
7. Se o workflow terminar verde, abra a execução e baixe o artefato `WitcherSigns-1.7.10` na seção **Artifacts**.
8. Extraia o artefato baixado e procure o `.jar` em `build/libs` (o artefato deverá conter o JAR gerado). Coloque esse `.jar` na pasta `mods` da instância Prism com Minecraft 1.7.10 + Forge.

## Limitação importante
Isso automatiza a tentativa de compilação, mas não garante que o projeto compile nem que o mod funcione em jogo. O Forge 1.7.10 usa ferramentas antigas, e o projeto precisa passar pela compilação e por testes reais. Se a execução falhar, abra o log da execução em Actions e compartilhe o erro aqui para que eu possa corrigir o projeto.

Não coloque o ZIP nem este workflow na pasta `mods`; somente um JAR compilado é instalado como mod.
