package com.tonikelope.megabasterd;

import static com.tonikelope.megabasterd.MainPanel.*;
import static com.tonikelope.megabasterd.MiscTools.*;
import java.awt.Color;
import java.awt.Dialog;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author tonikelope
 */
public final class FileGrabberDialog extends javax.swing.JDialog {

    private boolean _upload;
    private final ArrayList<File> _files;
    private String _base_path;
    private long _total_space;
    private final MainPanel _main_panel;
    private final boolean _remember_master_pass;
    private boolean _inserting_mega_accounts;

    public boolean isUpload() {
        return _upload;
    }

    public ArrayList<File> getFiles() {
        return _files;
    }

    public String getBase_path() {
        return _base_path;
    }

    public JComboBox<String> getAccount_combobox() {
        return account_combobox;
    }

    public JTextField getDir_name_textfield() {
        return dir_name_textfield;
    }

    public boolean isRemember_master_pass() {
        return _remember_master_pass;
    }

    public FileGrabberDialog(MainPanelView parent, boolean modal) {

        super(parent, modal);

        _main_panel = parent.getMain_panel();

        initComponents();

        updateFonts(this, DEFAULT_FONT, _main_panel.getZoom_factor());

        updateTitledBorderFont(((javax.swing.border.TitledBorder) jPanel1.getBorder()), DEFAULT_FONT, _main_panel.getZoom_factor());

        updateTitledBorderFont(((javax.swing.border.TitledBorder) jPanel2.getBorder()), DEFAULT_FONT, _main_panel.getZoom_factor());

        translateLabels(this);

        _total_space = 0L;
        _base_path = null;
        _upload = false;
        _inserting_mega_accounts = false;
        _remember_master_pass = true;
        _files = new ArrayList<>();

        dir_name_textfield.addMouseListener(new ContextMenuMouseListener());

        if (_main_panel.getMega_accounts().size() > 0) {

            THREAD_POOL.execute(new Runnable() {
                @Override
                public void run() {

                    swingInvoke(new Runnable() {
                        @Override
                        public void run() {

                            if (!_main_panel.getMega_active_accounts().isEmpty()) {
                                _inserting_mega_accounts = true;

                                for (Object o : _main_panel.getMega_accounts().keySet()) {

                                    account_combobox.addItem((String) o);
                                }

                                _inserting_mega_accounts = false;

                                for (Object o : _main_panel.getMega_active_accounts().keySet()) {

                                    account_combobox.setSelectedItem(o);

                                    account_comboboxItemStateChanged(null);

                                    break;
                                }

                            } else {

                                for (Object o : _main_panel.getMega_accounts().keySet()) {

                                    account_combobox.addItem((String) o);
                                }
                            }
                        }
                    });
                }

            });

        } else {

            used_space_label.setForeground(Color.red);
            used_space_label.setText(LabelTranslatorSingleton.getInstance().translate("No MEGA accounts available (Go to Settings > Accounts)"));
        }

        pack();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        file_tree_scrollpane = new javax.swing.JScrollPane();
        file_tree = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        dir_name_label = new javax.swing.JLabel();
        dir_name_textfield = new javax.swing.JTextField();
        account_label = new javax.swing.JLabel();
        account_combobox = new javax.swing.JComboBox<>();
        used_space_label = new javax.swing.JLabel();
        add_folder_button = new javax.swing.JButton();
        add_files_button = new javax.swing.JButton();
        dance_button = new javax.swing.JButton();
        total_file_size_label = new javax.swing.JLabel();
        warning_label = new javax.swing.JLabel();
        skip_rest_button = new javax.swing.JButton();
        skip_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("File Grabber");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Files"));

        file_tree.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        file_tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        file_tree.setDoubleBuffered(true);
        file_tree.setEnabled(false);
        file_tree.setRootVisible(false);
        file_tree_scrollpane.setViewportView(file_tree);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(file_tree_scrollpane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(file_tree_scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Upload info"));

        dir_name_label.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        dir_name_label.setText("Upload name:");
        dir_name_label.setDoubleBuffered(true);
        dir_name_label.setEnabled(false);

        dir_name_textfield.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dir_name_textfield.setDoubleBuffered(true);
        dir_name_textfield.setEnabled(false);

        account_label.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        account_label.setText("Account:");
        account_label.setDoubleBuffered(true);
        account_label.setEnabled(false);

        account_combobox.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        account_combobox.setDoubleBuffered(true);
        account_combobox.setEnabled(false);
        account_combobox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                account_comboboxItemStateChanged(evt);
            }
        });

        used_space_label.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        used_space_label.setText("Used space: 0.00GB");
        used_space_label.setDoubleBuffered(true);

        add_folder_button.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add_folder_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-folder-30.png"))); // NOI18N
        add_folder_button.setText("Add folder");
        add_folder_button.setDoubleBuffered(true);
        add_folder_button.setEnabled(false);
        add_folder_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_folder_buttonActionPerformed(evt);
            }
        });

        add_files_button.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add_files_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-file-30.png"))); // NOI18N
        add_files_button.setText("Add files");
        add_files_button.setDoubleBuffered(true);
        add_files_button.setEnabled(false);
        add_files_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_files_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dir_name_label)
                    .addComponent(account_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(add_files_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(add_folder_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dir_name_textfield)
                    .addComponent(account_combobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(used_space_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dir_name_label)
                    .addComponent(dir_name_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(account_label)
                    .addComponent(account_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(used_space_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_files_button)
                    .addComponent(add_folder_button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dance_button.setBackground(new java.awt.Color(102, 204, 255));
        dance_button.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        dance_button.setForeground(new java.awt.Color(255, 255, 255));
        dance_button.setText("Let's dance, baby");
        dance_button.setDoubleBuffered(true);
        dance_button.setEnabled(false);
        dance_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dance_buttonActionPerformed(evt);
            }
        });

        total_file_size_label.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        total_file_size_label.setText("[0 B]");
        total_file_size_label.setDoubleBuffered(true);
        total_file_size_label.setEnabled(false);

        warning_label.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        warning_label.setText("If you DO NOT want to transfer some folder or file you can REMOVE it (to select several items at the same time use CTRL + LMOUSE).");
        warning_label.setDoubleBuffered(true);
        warning_label.setEnabled(false);

        skip_rest_button.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        skip_rest_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        skip_rest_button.setText("REMOVE ALL EXCEPT THIS");
        skip_rest_button.setDoubleBuffered(true);
        skip_rest_button.setEnabled(false);
        skip_rest_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skip_rest_buttonActionPerformed(evt);
            }
        });

        skip_button.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        skip_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        skip_button.setText("REMOVE THIS");
        skip_button.setDoubleBuffered(true);
        skip_button.setEnabled(false);
        skip_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skip_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(warning_label)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(total_file_size_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(skip_rest_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(skip_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dance_button)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_file_size_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dance_button)
                    .addComponent(skip_rest_button)
                    .addComponent(skip_button))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_files_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_files_buttonActionPerformed

        add_files_button.setText(LabelTranslatorSingleton.getInstance().translate("Adding files, please wait..."));
        add_files_button.setEnabled(false);
        add_folder_button.setEnabled(false);
        warning_label.setEnabled(false);
        skip_button.setEnabled(false);
        skip_rest_button.setEnabled(false);
        dance_button.setEnabled(false);
        dir_name_textfield.setEnabled(false);
        dir_name_label.setEnabled(false);

        JFileChooser filechooser = new javax.swing.JFileChooser();

        updateFonts(filechooser, DEFAULT_FONT, (float) (_main_panel.getZoom_factor() * 1.25));

        filechooser.setDialogTitle("Add files");

        filechooser.setAcceptAllFileFilterUsed(false);

        filechooser.setMultiSelectionEnabled(true);

        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION && filechooser.getSelectedFile().canRead()) {

            total_file_size_label.setText("[0 B]");

            File[] files_selected = filechooser.getSelectedFiles();

            _base_path = files_selected[0].getParentFile().getAbsolutePath();

            dir_name_textfield.setText(files_selected[0].getParentFile().getName() + "_" + genID(10));

            dir_name_textfield.setEnabled(true);

            dir_name_label.setEnabled(true);

            DefaultMutableTreeNode root = new DefaultMutableTreeNode(filechooser.getSelectedFile().getParent());

            for (File file : files_selected) {

                DefaultMutableTreeNode current_file = new DefaultMutableTreeNode(file.getName() + (file.isFile() ? " [" + formatBytes(file.length()) + "]" : ""));

                root.add(current_file);
            }

            DefaultTreeModel tree_model = new DefaultTreeModel(sortTree(root));

            file_tree.setModel(tree_model);

            _genFileList();

            add_files_button.setEnabled(true);

            add_folder_button.setEnabled(true);

            add_files_button.setText(LabelTranslatorSingleton.getInstance().translate("Add files"));

            boolean root_childs = ((TreeNode) tree_model.getRoot()).getChildCount() > 0;

            file_tree.setRootVisible(root_childs);
            file_tree.setEnabled(root_childs);
            warning_label.setEnabled(root_childs);
            dance_button.setEnabled(root_childs);
            total_file_size_label.setEnabled(root_childs);
            skip_button.setEnabled(root_childs);
            skip_rest_button.setEnabled(root_childs);

        } else {

            if (filechooser.getSelectedFile() != null && !filechooser.getSelectedFile().canRead()) {

                JOptionPane.showMessageDialog(this, LabelTranslatorSingleton.getInstance().translate("File is not readable!"), "Error", JOptionPane.ERROR_MESSAGE);
            }

            boolean root_childs = ((TreeNode) file_tree.getModel().getRoot()).getChildCount() > 0;

            add_files_button.setText(LabelTranslatorSingleton.getInstance().translate("Add files"));
            add_files_button.setEnabled(true);
            add_folder_button.setEnabled(true);
            file_tree.setRootVisible(root_childs);
            file_tree.setEnabled(root_childs);
            warning_label.setEnabled(root_childs);
            dance_button.setEnabled(root_childs);
            total_file_size_label.setEnabled(root_childs);
            skip_button.setEnabled(root_childs);
            skip_rest_button.setEnabled(root_childs);
            dir_name_textfield.setEnabled(root_childs);
            dir_name_label.setEnabled(root_childs);
        }
    }//GEN-LAST:event_add_files_buttonActionPerformed

    private void add_folder_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_folder_buttonActionPerformed

        add_folder_button.setText(LabelTranslatorSingleton.getInstance().translate("Adding folder, please wait..."));

        add_files_button.setEnabled(false);
        add_folder_button.setEnabled(false);
        warning_label.setEnabled(false);
        skip_button.setEnabled(false);
        skip_rest_button.setEnabled(false);
        dance_button.setEnabled(false);
        dir_name_textfield.setEnabled(false);
        dir_name_label.setEnabled(false);

        JFileChooser filechooser = new javax.swing.JFileChooser();

        updateFonts(filechooser, DEFAULT_FONT, (float) (_main_panel.getZoom_factor() * 1.2));

        filechooser.setDialogTitle("Add directory");

        filechooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        filechooser.setAcceptAllFileFilterUsed(false);

        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION && filechooser.getSelectedFile().canRead()) {

            total_file_size_label.setText("[0 B]");

            _base_path = filechooser.getSelectedFile().getAbsolutePath();

            dir_name_textfield.setText(filechooser.getSelectedFile().getName() + "_" + genID(10));

            dir_name_textfield.setEnabled(true);

            dir_name_label.setEnabled(true);

            DefaultMutableTreeNode root = new DefaultMutableTreeNode(filechooser.getSelectedFile().getAbsolutePath());

            _genFileTree(filechooser.getSelectedFile().getAbsolutePath(), root);

            DefaultTreeModel tree_model = new DefaultTreeModel(sortTree(root));

            file_tree.setModel(tree_model);

            _genFileList();

            add_files_button.setEnabled(true);

            add_folder_button.setEnabled(true);

            add_folder_button.setText(LabelTranslatorSingleton.getInstance().translate("Add folder"));

            boolean root_childs = ((TreeNode) tree_model.getRoot()).getChildCount() > 0;

            file_tree.setRootVisible(root_childs);

            file_tree.setRootVisible(root_childs);
            file_tree.setEnabled(root_childs);
            warning_label.setEnabled(root_childs);
            dance_button.setEnabled(root_childs);
            total_file_size_label.setEnabled(root_childs);
            skip_button.setEnabled(root_childs);
            skip_rest_button.setEnabled(root_childs);

        } else {

            if (filechooser.getSelectedFile() != null && !filechooser.getSelectedFile().canRead()) {

                JOptionPane.showMessageDialog(this, LabelTranslatorSingleton.getInstance().translate("Folder is not readable!"), "Error", JOptionPane.ERROR_MESSAGE);
            }

            boolean root_childs = ((TreeNode) file_tree.getModel().getRoot()).getChildCount() > 0;

            add_folder_button.setText(LabelTranslatorSingleton.getInstance().translate("Add folder"));
            add_files_button.setEnabled(true);
            add_folder_button.setEnabled(true);
            file_tree.setRootVisible(root_childs);
            file_tree.setEnabled(root_childs);
            warning_label.setEnabled(root_childs);
            dance_button.setEnabled(root_childs);
            total_file_size_label.setEnabled(root_childs);
            skip_button.setEnabled(root_childs);
            skip_rest_button.setEnabled(root_childs);
            dir_name_textfield.setEnabled(root_childs);
            dir_name_label.setEnabled(root_childs);
        }

    }//GEN-LAST:event_add_folder_buttonActionPerformed

    private void dance_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dance_buttonActionPerformed

        _upload = true;

        setVisible(false);
    }//GEN-LAST:event_dance_buttonActionPerformed

    private void account_comboboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_account_comboboxItemStateChanged

        String selected_item = (String) account_combobox.getSelectedItem();

        if (!_inserting_mega_accounts && selected_item != null) {

            final String email = selected_item;

            final Dialog tthis = this;

            used_space_label.setForeground(new Color(102, 102, 102));

            used_space_label.setText(LabelTranslatorSingleton.getInstance().translate("Checking account quota, please wait..."));

            account_combobox.setEnabled(false);
            account_label.setEnabled(false);
            dance_button.setEnabled(false);
            add_files_button.setEnabled(false);
            add_folder_button.setEnabled(false);
            dir_name_textfield.setEnabled(false);
            total_file_size_label.setEnabled(false);
            skip_button.setEnabled(false);
            skip_rest_button.setEnabled(false);
            warning_label.setEnabled(false);
            file_tree.setEnabled(false);

            THREAD_POOL.execute(new Runnable() {
                @Override
                public void run() {

                    MegaAPI ma = null;
                    try {
                        ma = checkMegaAccountLoginAndShowMasterPassDialog(_main_panel, tthis, email);
                    } catch (Exception ex) {

                    }

                    Long[] quota = null;

                    if (ma != null) {
                        quota = ma.getQuota();
                    }

                    if (isDisplayable()) {
                        if (quota != null) {

                            final Color used_space_color;

                            if (quota[0] <= Math.round((double) quota[1] / 2)) {

                                used_space_color = new Color(0, 170, 0);

                            } else if (quota[0] < quota[1]) {

                                used_space_color = new Color(230, 115, 0);

                            } else {

                                used_space_color = Color.red;
                            }

                            final String quota_m = LabelTranslatorSingleton.getInstance().translate("Quota used: ") + formatBytes(quota[0]) + "/" + formatBytes(quota[1]);

                            swingInvoke(new Runnable() {
                                @Override
                                public void run() {
                                    boolean root_childs = ((TreeNode) file_tree.getModel().getRoot()).getChildCount() > 0;

                                    used_space_label.setText(quota_m);

                                    used_space_label.setForeground(used_space_color);

                                    for (JComponent c : new JComponent[]{add_files_button, add_folder_button, account_combobox, account_label}) {

                                        c.setEnabled(true);
                                    }

                                    for (JComponent c : new JComponent[]{dir_name_textfield, dir_name_label, warning_label, dance_button, file_tree, total_file_size_label, skip_button, skip_rest_button}) {

                                        c.setEnabled(root_childs);
                                    }
                                }
                            });

                        } else {

                            swingInvoke(new Runnable() {
                                @Override
                                public void run() {

                                    account_combobox.setEnabled(true);

                                    account_label.setEnabled(true);

                                    account_combobox.setSelectedIndex(-1);

                                    used_space_label.setForeground(Color.red);

                                    used_space_label.setText(LabelTranslatorSingleton.getInstance().translate("ERROR checking account quota!"));
                                }
                            });

                        }
                    }
                }
            });
        }

    }//GEN-LAST:event_account_comboboxItemStateChanged

    private void skip_rest_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skip_rest_buttonActionPerformed

        if (deleteAllExceptSelectedTreeItems(file_tree)) {

            _genFileList();

            boolean root_childs = ((TreeNode) file_tree.getModel().getRoot()).getChildCount() > 0;

            warning_label.setEnabled(root_childs);
            dance_button.setEnabled(root_childs);
            total_file_size_label.setEnabled(root_childs);
            skip_button.setEnabled(root_childs);
            skip_rest_button.setEnabled(root_childs);
            dir_name_textfield.setEnabled(root_childs);
            dir_name_label.setEnabled(root_childs);

            if (!root_childs) {

                dir_name_textfield.setText("");
            }

        }
    }//GEN-LAST:event_skip_rest_buttonActionPerformed

    private void skip_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skip_buttonActionPerformed

        if (deleteSelectedTreeItems(file_tree)) {

            _genFileList();

            boolean root_childs = ((TreeNode) file_tree.getModel().getRoot()).getChildCount() > 0;

            warning_label.setEnabled(root_childs);
            dance_button.setEnabled(root_childs);
            total_file_size_label.setEnabled(root_childs);
            skip_button.setEnabled(root_childs);
            skip_rest_button.setEnabled(root_childs);
            dir_name_textfield.setEnabled(root_childs);
            dir_name_label.setEnabled(root_childs);

            if (!root_childs) {

                dir_name_textfield.setText("");
            }
        }
    }//GEN-LAST:event_skip_buttonActionPerformed

    private void _genFileTree(String directoryName, DefaultMutableTreeNode root) {

        File directory = new File(directoryName);

        File[] fList = directory.listFiles();

        if (fList != null) {

            for (File file : fList) {

                if (file.isFile() && file.canRead()) {

                    DefaultMutableTreeNode current_file = new DefaultMutableTreeNode(file.getName() + " [" + formatBytes(file.length()) + "]");

                    root.add(current_file);

                } else if (file.isDirectory() && file.canRead() && file.listFiles().length > 0) {

                    DefaultMutableTreeNode current_dir = new DefaultMutableTreeNode(file.getName());

                    root.add(current_dir);

                    _genFileTree(file.getAbsolutePath(), current_dir);
                }
            }

        }
    }

    private void _genFileList() {

        _files.clear();

        _total_space = 0L;

        DefaultTreeModel tree_model = (DefaultTreeModel) file_tree.getModel();

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree_model.getRoot();

        Enumeration files_tree = root.depthFirstEnumeration();

        while (files_tree.hasMoreElements()) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) files_tree.nextElement();

            if (node.isLeaf() && node != root) {

                String path = "";

                Object[] object_path = node.getUserObjectPath();

                for (Object p : object_path) {

                    path += "/" + p;
                }

                path = path.replaceAll("^//", "/").trim().replaceAll(" \\[[0-9,.]+ [A-Z]+\\]$", "");

                File file = new File(path);

                if (file.isFile()) {

                    _total_space += file.length();

                    _files.add(file);
                }
            }
        }

        total_file_size_label.setText("[" + formatBytes(_total_space) + "]");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> account_combobox;
    private javax.swing.JLabel account_label;
    private javax.swing.JButton add_files_button;
    private javax.swing.JButton add_folder_button;
    private javax.swing.JButton dance_button;
    private javax.swing.JLabel dir_name_label;
    private javax.swing.JTextField dir_name_textfield;
    private javax.swing.JTree file_tree;
    private javax.swing.JScrollPane file_tree_scrollpane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton skip_button;
    private javax.swing.JButton skip_rest_button;
    private javax.swing.JLabel total_file_size_label;
    private javax.swing.JLabel used_space_label;
    private javax.swing.JLabel warning_label;
    // End of variables declaration//GEN-END:variables
}
