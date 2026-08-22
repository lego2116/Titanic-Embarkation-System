package view;

import model.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TelaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    private final Color AZUL_OCEANO_ESC = new Color(20, 48, 75);
    private final Color MADEIRA_ESCURA = new Color(74, 38, 20);
    private final Color MADEIRA_CLARA = new Color(112, 60, 32);
    private final Color OURO_BORDA = new Color(218, 165, 32);
    private final Color PAPEL_PERGAMINHO = new Color(243, 233, 210);
    private final Color TEXTO_ESCURO = new Color(35, 20, 10);

    private final Font FONTE_TITULO = new Font("Georgia", Font.BOLD, 26);
    private final Font FONTE_SUBTITULO = new Font("Georgia", Font.ITALIC, 14);
    private final Font FONTE_ROTULO = new Font("Georgia", Font.BOLD, 12);
    private final Font FONTE_INPUT = new Font("SansSerif", Font.PLAIN, 13);
    private final Font FONTE_LIVRO = new Font("Georgia", Font.PLAIN, 13);

    private JTextField txtNome;
    private JTextField txtIdade;
    private JComboBox<String> cbSexo;
    private JComboBox<String> cbClasse;
    private JComboBox<String> cbCargo;
    private JTextArea txtResultado;

    private ArrayList<Pessoa> lista = new ArrayList<>();

    public TelaPrincipal() {
        setTitle("Titanic Embarkation Management System");
        setSize(980, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelGeral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, AZUL_OCEANO_ESC, 0, getHeight(), new Color(10, 25, 40));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(OURO_BORDA);
                g2.setStroke(new BasicStroke(4));
                g2.drawRoundRect(10, 10, getWidth() - 25, getHeight() - 48, 20, 20);
            }
        };
        painelGeral.setLayout(null);
        setContentPane(painelGeral);

        JLabel lblFoto = new JLabel();
        lblFoto.setBounds(30, 25, 340, 140);
        lblFoto.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(OURO_BORDA, 3, true),
                new LineBorder(MADEIRA_ESCURA, 2)
        ));
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/titanic.png"));
            Image img = icon.getImage().getScaledInstance(340, 140, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblFoto.setText("<html><center>[ Coloque titanic.png no src ]</center></html>");
            lblFoto.setForeground(OURO_BORDA);
            lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        }
        painelGeral.add(lblFoto);

        JLabel lblTitulo = new JLabel("Titanic Embarkation System", SwingConstants.CENTER);
        lblTitulo.setBounds(400, 45, 530, 40);
        lblTitulo.setFont(FONTE_TITULO);
        lblTitulo.setForeground(new Color(245, 225, 170));
        painelGeral.add(lblTitulo);

        JLabel lblSub = new JLabel("White Star Line — Southampton, 1912", SwingConstants.CENTER);
        lblSub.setBounds(400, 85, 530, 25);
        lblSub.setFont(FONTE_SUBTITULO);
        lblSub.setForeground(OURO_BORDA);
        painelGeral.add(lblSub);

        JPanel painelForm = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, MADEIRA_CLARA, 0, getHeight(), MADEIRA_ESCURA);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                g2.setColor(OURO_BORDA);
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);
            }
        };
        painelForm.setBounds(30, 185, 430, 330);
        painelForm.setLayout(null);
        painelForm.setOpaque(false);

        JLabel lblPlaca = new JLabel("Dados de Embarque", SwingConstants.CENTER);
        lblPlaca.setBounds(120, 10, 190, 28);
        lblPlaca.setFont(FONTE_ROTULO);
        lblPlaca.setForeground(new Color(245, 235, 205));
        lblPlaca.setBorder(new LineBorder(OURO_BORDA, 2, true));
        painelForm.add(lblPlaca);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(25, 45, 120, 20);
        lblNome.setFont(FONTE_ROTULO);
        lblNome.setForeground(PAPEL_PERGAMINHO);
        painelForm.add(lblNome);

        txtNome = criarCampoTexto();
        txtNome.setBounds(25, 68, 250, 32);
        painelForm.add(txtNome);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(290, 45, 100, 20);
        lblIdade.setFont(FONTE_ROTULO);
        lblIdade.setForeground(PAPEL_PERGAMINHO);
        painelForm.add(lblIdade);

        txtIdade = criarCampoTexto();
        txtIdade.setBounds(290, 68, 115, 32);
        txtIdade.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        painelForm.add(txtIdade);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(25, 115, 120, 20);
        lblSexo.setFont(FONTE_ROTULO);
        lblSexo.setForeground(PAPEL_PERGAMINHO);
        painelForm.add(lblSexo);

        cbSexo = new JComboBox<>(new String[]{"Feminino", "Masculino"});
        estilizarComboBox(cbSexo);
        cbSexo.setBounds(25, 138, 175, 32);
        painelForm.add(cbSexo);

        JLabel lblClasse = new JLabel("Classe (Passageiro):");
        lblClasse.setBounds(225, 115, 150, 20);
        lblClasse.setFont(FONTE_ROTULO);
        lblClasse.setForeground(PAPEL_PERGAMINHO);
        painelForm.add(lblClasse);

        cbClasse = new JComboBox<>(new String[]{"-", "1ª", "2ª", "3ª"});
        estilizarComboBox(cbClasse);
        cbClasse.setBounds(225, 138, 180, 32);
        painelForm.add(cbClasse);

        JLabel lblCargo = new JLabel("Cargo (Tripulante):");
        lblCargo.setBounds(25, 185, 250, 20);
        lblCargo.setFont(FONTE_ROTULO);
        lblCargo.setForeground(PAPEL_PERGAMINHO);
        painelForm.add(lblCargo);

        String[] cargos = {"-", "Capitão", "Oficial de Bordo", "Comissário (Steward)", "Engenheiro / Foguista", "Marinheiro / Vigia", "Cozinheiro"};
        cbCargo = new JComboBox<>(cargos);
        estilizarComboBox(cbCargo);
        cbCargo.setBounds(25, 208, 380, 32);
        painelForm.add(cbCargo);

        painelGeral.add(painelForm);

        JButton btnCadastrar = criarBotaoMadeira("Cadastrar");
        btnCadastrar.setBounds(120, 530, 250, 55);
        painelGeral.add(btnCadastrar);

        JPanel painelLivro = new JPanel(null);
        painelLivro.setBounds(485, 185, 455, 400);
        painelLivro.setBackground(PAPEL_PERGAMINHO);
        painelLivro.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(OURO_BORDA, 3, true),
                BorderFactory.createMatteBorder(0, 15, 0, 0, MADEIRA_ESCURA)
        ));

        JLabel lblCabecalhoLivro = new JLabel("Lista de Embarque", SwingConstants.CENTER);
        lblCabecalhoLivro.setBounds(20, 10, 415, 30);
        lblCabecalhoLivro.setFont(new Font("Georgia", Font.BOLD, 16));
        lblCabecalhoLivro.setForeground(TEXTO_ESCURO);
        lblCabecalhoLivro.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, OURO_BORDA));
        painelLivro.add(lblCabecalhoLivro);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setBackground(PAPEL_PERGAMINHO);
        txtResultado.setForeground(TEXTO_ESCURO);
        txtResultado.setFont(FONTE_LIVRO);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);

        JScrollPane scrollLivro = new JScrollPane(txtResultado);
        scrollLivro.setBounds(20, 45, 420, 340);
        scrollLivro.setBorder(null);
        scrollLivro.setOpaque(false);
        scrollLivro.getViewport().setOpaque(false);
        painelLivro.add(scrollLivro);

        painelGeral.add(painelLivro);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, 
                            "Erro: O campo 'Nome' está vazio. Preencha o nome!", 
                            "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String textoIdade = txtIdade.getText().trim();
                if (textoIdade.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, 
                            "Erro: O campo 'Idade' está vazio. Informe a idade!", 
                            "Campo Obrigatório", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idade;
                try {
                    idade = Integer.parseInt(textoIdade);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, 
                            "Erro: A idade digitada é inválida!", 
                            "Valor Inválido", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sexo = (String) cbSexo.getSelectedItem();
                String classeSel = (String) cbClasse.getSelectedItem();
                String cargoSel = (String) cbCargo.getSelectedItem();

                boolean temClasse = !classeSel.equals("-");
                boolean temCargo = !cargoSel.equals("-");

                if (!temClasse && !temCargo) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, 
                            "Erro: Selecione uma 'Classe' (para Passageiro) OU um 'Cargo' (para Tripulante)!", 
                            "Seleção Necessária", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (temClasse && temCargo) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, 
                            "Erro: Você selecionou Classe E Cargo ao mesmo tempo.\nDeixe '-' na Classe se for Tripulante, ou '-' no Cargo se for Passageiro!", 
                            "Seleção Duplicada", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (temClasse) {
                    int classe = Integer.parseInt(classeSel.replace("ª", "").trim());
                    Passageiro p = new Passageiro(nome, sexo, idade, classe);
                    lista.add(p);
                } else {
                    Tripulante t = new Tripulante(nome, sexo, idade, cargoSel);
                    lista.add(t);
                }

                atualizarLista();
                limparCampos();
            }
        });
    }

    private void atualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            Pessoa p = lista.get(i);
            sb.append("[• ").append(i + 1).append("] ")
              .append(p.toString())
              .append("\n\n");
        }
        txtResultado.setText(sb.toString());
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        cbClasse.setSelectedIndex(0);
        cbCargo.setSelectedIndex(0);
        txtNome.requestFocus();
    }

    private JTextField criarCampoTexto() {
        JTextField tf = new JTextField();
        tf.setFont(FONTE_INPUT);
        tf.setBackground(PAPEL_PERGAMINHO);
        tf.setForeground(TEXTO_ESCURO);
        tf.setBorder(new LineBorder(OURO_BORDA, 2, true));
        return tf;
    }

    private void estilizarComboBox(JComboBox<?> cb) {
        cb.setFont(FONTE_INPUT);
        cb.setBackground(PAPEL_PERGAMINHO);
        cb.setForeground(TEXTO_ESCURO);
        cb.setBorder(new LineBorder(OURO_BORDA, 2, true));
    }

    private JButton criarBotaoMadeira(String texto) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(230, 190, 100), 0, getHeight(), new Color(170, 120, 40));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                g2.setColor(MADEIRA_ESCURA);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20);

                super.paintComponent(g);
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Georgia", Font.BOLD, 15));
        btn.setForeground(MADEIRA_ESCURA);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}